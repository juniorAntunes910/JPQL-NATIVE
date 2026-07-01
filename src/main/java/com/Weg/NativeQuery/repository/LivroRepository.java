package com.Weg.NativeQuery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Weg.NativeQuery.dto.EstatisticaEditoraDTO;
import com.Weg.NativeQuery.model.Editora;
import com.Weg.NativeQuery.model.Livro;
import com.Weg.NativeQuery.projection.AutorNomeNacionalidade;
import com.Weg.NativeQuery.projection.LivroMinimoProjection;

import java.math.BigDecimal;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);

    List<Livro> findByCategoriaAndPrecoBefore(String categoria, BigDecimal Preco);

    List<Livro> findByPrecoBetween(BigDecimal min, BigDecimal max);

    List<Livro> findByCategoriaIn(List<String> categorias);

    List<Livro> findByIsbnIsNull();

    List<Livro> findByEditoraOrderByTituloAsc(Editora editora);

    Long countByAutoresNacionalidade(String nacionalidade);

    // JPQL
    @Query("SELECT l.titulo FROM Livro l WHERE l.categoria = :categoria")
    List<String> apenasTitulosPorCategoria(@Param("categoria") String categoria);

    @Query("SELECT l FROM Livro l JOIN Autor a WHERE a.nome = :nomeAutor ")
    List<Livro> nomeAutorIgualParametro(@Param("nomeAutor") String nomeAutor);

    @Query("SELECT l FROM Livro l JOIN FETCH Autor a WHERE l.id = :idLivro")
    List<Livro> buscarComFetch(@Param("idLivro") Long idLivro);

    @Query("SELECT AVG(l.preco) FROM Livro l WHERE l.editora = :editora")
    BigDecimal mediaPrecoLivrosDeEditora(@Param("editora") Editora editora);

    @Query("SELECT l FROM Livro l WHERE l.preco > (SELECT AVG(l2.preco) FROM Livro l2)")
    List<Livro> livrosPrecoMaiorMedia();

    // Native

    @NativeQuery("""
                SELECT l.id,
                l.titulo,
                l.isbn,
                l.preco,
                l.dataPublicacao,
                l.categoria,
                l.editora,
                l.autores
                FROM Livro l
                WHERE l.dataPublicacao = '2023'
            """)
    List<Livro> livrosDataPublicacao2023();

    @NativeQuery("""
                SELECT l.id,
                l.titulo,
                l.isbn,
                l.preco,
                l.dataPublicacao,
                l.categoria,
                l.editora,
                l.autores
                FROM livro l
                JOIN autor a
                ON l.autor_id = a.id
                WHERE a.nacionalidade = 'brasileiro'
            """)
    List<Livro> livrosAutoresBrasileiros();

    @NativeQuery("""
                SELECT l.id,
                l.titulo,
                l.isbn,
                l.preco,
                l.dataPublicacao,
                l.categoria,
                l.editora,
                l.autores
                FROM livro l
                WHERE l.categoria  = LOWER(:categoriaNome)
            """)
    List<Livro> buscarPorCategoriaIgnorando(@Param("categoriaNome") String categoriaNome);

    // Projection e DTOs
    @NativeQuery("""
            SELECT l.nome, l.preco
            FROM Livro l
            """)
    List<LivroMinimoProjection> buscarLivroMinimo();

    @Query("""
                SELECT NEW
                com.Weg.NativeQuery.dto.EstatisticaEditoraDTO
                (l.editora.nome,
                COUNT(l.id))
                FROM Livro l
                GROUP BY l.editora.nome
            """)
    List<EstatisticaEditoraDTO> retornaNomeEContagem();

    @Query("""
            SELECT a.nome as nomeAutor, a.nacionalidade as nacionalidadeAutor
            FROM Autor a
            """)
    List<AutorNomeNacionalidade> retornaAutorMinimo();

    <T> List<T> findBy(Class<T> type);

}
