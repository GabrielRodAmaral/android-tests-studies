package br.com.alura.orgs

import br.com.alura.orgs.model.Usuario
import org.junit.Assert
import org.junit.Test

class UsuarioTests {

    @Test
    fun usuarioDeveSerValidoCasoTenhaEmailESenhaValidos() {
        val user = Usuario(
            id = "1",
            email = "gabriel@gmail.com",
            senha = "1234567"
        )

        val isValid = user.ehValido()

        Assert.assertEquals(true, isValid)
    }

    @Test
    fun usuarioDeveSerInvalidoCasoSenhaPossuaMenosDeSeisCaracteres() {
        val user = Usuario(
            id = "1",
            email = "gabriel@gmail.com",
            senha = "12345"
        )

        val isValid = user.ehValido()

        Assert.assertEquals(false, isValid)
    }

    @Test
    fun usuarioDeveSerInvalidoCasoEmailSejaInvalido() {
        val user = Usuario(
            id = "1",
            email = "gabriel.com",
            senha = "1234567"
        )

        val isValid = user.ehValido()

        Assert.assertEquals(false, isValid)
    }
}