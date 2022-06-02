package jp.classmethod.sample.spring

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

// 3. Add Controller
@RestController
class RouteController {

    @GetMapping("/health")
    fun health(): ResponseEntity<Any> {
        return ResponseEntity.ok(HealthMessage(messageTimestamp = Instant.now().epochSecond, message = "I'm fine"))
    }

    /**
     * Profile Endpoint.
     * Cognito で付与した Jwt Token から Issuer を特定し、
     * Issuer に対応する Tenant 情報を付与した上で User 情報を返す
     *
     */
    @GetMapping("/profile")
    fun profile(): ResponseEntity<UserInfo> {
        TODO("not implement")
    }
}

data class HealthMessage (
    val messageTimestamp: Long,
    val message: String
)
