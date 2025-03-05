package by.meshicage.service.cache;

import by.meshicage.applicationexceptionstarter.exception.impl.genre.GenreNotFoundException;
import by.meshicage.entity.GenreEntity;
import by.meshicage.repository.GenreRepository;
import by.meshicage.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreCacheService implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    @Cacheable(value = "genre", key = "#id")
    public GenreEntity findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new GenreNotFoundException(id));
    }
}
