package com.Weg.NativeQuery.services;

import org.springframework.stereotype.Service;

import com.Weg.NativeQuery.dto.AutorResponse;
import com.Weg.NativeQuery.mapper.AutorMapper;
import com.Weg.NativeQuery.repository.AutorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutorService {
    
    private final AutorMapper autorMapper;
    private final AutorRepository autorRepository;


    public AutorResponse create(){
        
    }

}
