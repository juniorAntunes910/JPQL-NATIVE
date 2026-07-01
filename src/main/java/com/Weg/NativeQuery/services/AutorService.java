package com.Weg.NativeQuery.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Weg.NativeQuery.dto.AutorRequest;
import com.Weg.NativeQuery.dto.AutorResponse;
import com.Weg.NativeQuery.mapper.AutorMapper;
import com.Weg.NativeQuery.model.Autor;
import com.Weg.NativeQuery.repository.AutorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorMapper autorMapper;
    private final AutorRepository autorRepository;

    public AutorResponse create(AutorRequest autorRequest) {
        Autor autor = autorMapper.toEntity(autorRequest);
        autorRepository.save(autor);
        return autorMapper.toResponse(autor);
    }

    public List<AutorResponse> readAll() {
        List<Autor> autors = autorRepository.findAll();
        return autors.stream().map(autorMapper::toResponse).toList();
    }

    public AutorResponse readById(Long id){
        Autor autor = autorRepository.findById(id).orElse(null);
        if(autor == null){
            throw new RuntimeException("Autor não existe");
        }
        return autorMapper.toResponse(autor);
    }

    public AutorResponse update(Long id, AutorRequest autorRequest){
        Autor autor = autorRepository.findById(id).orElse(null);
        if(autor == null){
            throw new RuntimeException("Autor não existe");
        }
        autor.setNacionalidade(autorRequest.nacionalidade());
        autor.setNome(autorRequest.nome());
        autorRepository.save(autor);
        return autorMapper.toResponse(autor);
    }

    public void delete(Long id){
        Autor autor = autorRepository.findById(id).orElse(null);
        if(autor == null){
            throw new RuntimeException("Autor não existe");
        }
        autorRepository.delete(autor);
    }

}
