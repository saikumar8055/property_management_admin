package com.property.management.controller;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final OwnerService ownerService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO> signUp(@Valid @RequestBody OwnerSignUpRequestDTO request) {
        return ResponseEntity.ok(ownerService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDTO> signIn(@Valid @RequestBody OwnerSignInRequestDTO request) {
        return ResponseEntity.ok(ownerService.signIn(request));
    }
}