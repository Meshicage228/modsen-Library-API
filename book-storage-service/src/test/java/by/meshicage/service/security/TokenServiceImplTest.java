package by.meshicage.service.security;

import by.meshicage.client.UserClient;
import by.meshicage.dto.user.UserRequestDto;
import by.meshicage.dto.user.UserResponseDto;
import by.meshicage.service.security.impl.SecretKeyGeneratorHS256;
import by.meshicage.service.security.impl.TokenServiceImpl;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {
    @Mock
    private UserClient userClient;

    @Mock
    private SecretKeyGeneratorHS256 generator;

    @InjectMocks
    private TokenServiceImpl tokenService;

    private static SecretKey secretKey;
    private static final String TEST_USERNAME = "testUser ";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String TEST_ROLE = "USER";
    private static final String JWT_START = "eyJ";
    private static final long TEST_EXPIRY_TIME = 100000;
    private UserRequestDto userRequestDto;
    private UserResponseDto userResponseDto;

    @BeforeAll
    static void beforeAll() {
        SecretKeyGeneratorHS256 secretKeyGeneratorHS256 = new SecretKeyGeneratorHS256();
        secretKey = secretKeyGeneratorHS256.generateSecretKey();
    }

    @BeforeEach
    public void setUp() {
        when(generator.generateSecretKey()).thenReturn(secretKey);
        userRequestDto = new UserRequestDto(TEST_USERNAME, TEST_PASSWORD);
        userResponseDto = new UserResponseDto(1L, TEST_USERNAME, TEST_ROLE, "USER");
        tokenService.init();
    }

    @Test
    @DisplayName("Create token successfully")
    public void testCreateToken() {
        // Given
        when(userClient.login(TEST_USERNAME, TEST_PASSWORD)).thenReturn(userResponseDto);

        // When
        String token = tokenService.createToken(userRequestDto);

        // Then
        assertNotNull(token);
        assertTrue(token.startsWith(JWT_START));
    }

    @Test
    @DisplayName("Extract authentication from token")
    public void testFromToken() {
        // Given
        String token = Jwts.builder()
                .setSubject(TEST_USERNAME)
                .setExpiration(new Date(System.currentTimeMillis() + TEST_EXPIRY_TIME))
                .claim("role", TEST_ROLE)
                .claim("username", TEST_USERNAME)
                .signWith(secretKey)
                .compact();

        // When
        Authentication authentication = tokenService.fromToken(token);

        // Then
        assertNotNull(authentication);
        assertEquals(TEST_USERNAME, authentication.getName());
        assertEquals(1, authentication.getAuthorities().size());
        assertEquals("ROLE_USER", authentication.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    @DisplayName("Throw exception when token is invalid")
    public void testFromTokenThrowsException() {
        // Given
        String invalidToken = "invalid.token";

        // When / Then
        assertThrows(Exception.class, () -> tokenService.fromToken(invalidToken));
    }
}