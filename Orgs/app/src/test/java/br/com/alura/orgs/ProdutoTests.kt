package br.com.alura.orgs

import br.com.alura.orgs.model.Produto
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeFalse
import org.junit.Test
import java.math.BigDecimal

class ProdutoTests {

    @Test
    fun `Deve retornar True quando o valor for valido`() {
        val produtoValido = Produto(
            nome = "Banana",
            descricao = "Banana Prata",
            valor = BigDecimal("6.99")
        )

        val valorEValido = produtoValido.valorEValido

        // Usando junit
//        assertTrue(valorEValido)

        // Usando kluent
        valorEValido shouldBeEqualTo true
    }

    @Test
    fun deveRetornarFalseQuandoOValorForMaiorQueCemReais() {
        //Arrange
        val produtoInvalido = Produto(
            nome = "Banana",
            descricao = "Banana Maça",
            valor = BigDecimal("145.00")
        )

        //Act
        val valorEhValido = produtoInvalido.valorEValido

        // Com junit
//        assertFalse(valorEhValido)

        //Com kluent
        valorEhValido shouldBeEqualTo false
    }

    @Test
    fun deveRetornarFalseQuandoOValorForMenorOuIgualAZero() {
        val produtoComValorIgualAZero = Produto(
            nome = "Banana",
            descricao = "Banana nanica",
            valor = BigDecimal("0")
        )

        val produtoComValorMenorQueZero = Produto(
            nome = "Banana",
            descricao = "Banana rara",
            valor = BigDecimal("-13.0")
        )

        val valorIgualAZeroEhValido = produtoComValorIgualAZero.valorEValido

        val valorMenorQueZeroEhValido = produtoComValorMenorQueZero.valorEValido

//        Com junit
//        assertFalse(valorIgualAZeroEhValido)
//        assertFalse(valorMenorQueZeroEhValido)

//        Com kluent (usando uma forma mais específica)
        valorIgualAZeroEhValido.shouldBeFalse()
        valorMenorQueZeroEhValido.shouldBeFalse()
    }
}