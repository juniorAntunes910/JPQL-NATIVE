package com.Weg.NativeQuery.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Weg.NativeQuery.dto.EditoraRequest;
import com.Weg.NativeQuery.dto.EditoraResponse;
import com.Weg.NativeQuery.mapper.EditoraMapper;
import com.Weg.NativeQuery.model.Editora;
import com.Weg.NativeQuery.repository.EditoraRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EditoraService {

    private final EditoraMapper editoraMapper;
    private final EditoraRepository editoraRepository;

    public EditoraResponse create(EditoraRequest editoraRequest){
        Editora editora = editoraMapper.toEntity(editoraRequest);
        editoraRepository.save(editora); 
        return editoraMapper.toREsponse(editora);       
    }

    public List<EditoraResponse> readAll(){
        List<Editora> editoras = editoraRepository.findAll();
        return editoras.stream().map(editoraMapper::toREsponse).toList();
    }

    public EditoraResponse readById(Long id){
        Editora editora = editoraRepository.findById(id).orElse(null);
        if(editora == null){
            throw new RuntimeException("A editora não existe");
        }
        return editoraMapper.toREsponse(editora);
    }

    public EditoraResponse update(Long id, EditoraRequest editoraRequest){
        Editora editora = editoraRepository.findById(id).orElse(null);
        if(editora == null){
            throw new RuntimeException("A editora não existe");
        }
        editora.setNome(editoraRequest.nome());
        editoraRepository.save(editora);
        return editoraMapper.toREsponse(editora);
    }

    public void delete(long id){
        Editora editora = editoraRepository.findById(id).orElse(null);
        if(editora == null){
            throw new RuntimeException("Editora não existe");
        }
        editoraRepository.delete(editora);
    }

}
