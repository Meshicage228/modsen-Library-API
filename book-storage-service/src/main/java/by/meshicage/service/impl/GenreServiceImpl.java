package by.meshicage.service.impl;

import by.meshicage.entity.GenreEntity;
import by.meshicage.service.GenreService;
import by.meshicage.service.cache.GenreCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreCacheService genreCacheService;

    @Override
    public GenreEntity findById(Long id) {
        return genreCacheService.findById(id);
    }
}
