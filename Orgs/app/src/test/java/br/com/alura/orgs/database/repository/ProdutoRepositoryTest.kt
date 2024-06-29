package br.com.alura.orgs.database.repository

import android.content.Context
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.database.dao.ProdutoDao
import br.com.alura.orgs.model.Produto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.math.BigDecimal


class ProdutoRepositoryTest {

    @Test
    fun `deve chamar o dao quando salva um produto`() = runTest {
//        Arrange
        // usando mockK para mockar o context

        // isso não funciona bem porque apesar de mockar o context não mocka as funcionalidades
//        do context, então mockamos a classe em si
//        val context = mockk<Context>()
//        val dao = AppDatabase.instancia(context).produtoDao()
        val dao = mockk<ProdutoDao>()
        val produtoRepository = ProdutoRepository(dao)
        val produto = Produto(
            nome = "batata",
            descricao = "banana nanica",
            valor = BigDecimal("55.60")
        )

        // o every mocka o retorno da função do dao para que não de erro quando a mesma for chamada
        // invés de retorno unit poderiamos colocar mockk() e funcionaria, mas Unit fica mais
        // específico
//      Como o mockK é feito pro kotlin ele tem suporte a coroutines, então para utilizar coroutines
//      e chamar por exemplo uma função suspend, basta colocar co, exemplo invés de every coEvery
        coEvery {
            dao.salva(produto)
        }.returns(Unit)

        // Act
        produtoRepository.salva(produto)

        // Assert
//        Função do mockK que permite verificar se a função do dao está sendo chamada
        coVerify {
            dao.salva(produto)
        }
    }
}