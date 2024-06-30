package br.com.alura.orgs

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.ui.activity.FormularioProdutoActivity
import br.com.alura.orgs.ui.activity.ListaProdutosActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProdutoTelasTest {

    @get:Rule
    val rule = ActivityScenarioRule(ListaProdutosActivity ::class.java)

    @Before
    fun preparaAmbiente() {
        AppDatabase.instancia(InstrumentationRegistry.getInstrumentation().targetContext).clearAllTables()
    }

    @Test
    fun deveMostrarONomeDoAplicativo() {
        /*
        No exemplo abaixo o Espresso lança a activity ListaProdutosActivity, assim fazendo possível
        testa-la
        encontra um elemento da tela usando o view matchers
        buscando um elemento com o texto igual a Orgs
        depois ele no check faz uma asserção se a view encontrada está visível ao usuário
         */
        Espresso
            .onView(ViewMatchers.withText("Orgs"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun deveMostrarCamposNecessariosParaCriarUmProduto() {
        clicaNoFab()
        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_nome))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_descricao))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_valor))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_botao_salvar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun deveSerCapazDePreencherOsCamposESalvar() {

        clicaNoFab()

        preencheCamposDoFormulario("banana", "Banana nanica", "6.99")

        clicaNoBotaoDeSalvar()

        Espresso
            .onView(ViewMatchers.withText("banana"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun deveSerCapazDeEditarUmProduto() {

        clicaNoFab()

        preencheCamposDoFormulario("maça", "fruta gostosa", "5.17")

        clicaNoBotaoDeSalvar()


        Espresso
            .onView(ViewMatchers.withText("maça"))
            .perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.menu_detalhes_produto_editar))
            .perform(ViewActions.click())

        preencheCamposDoFormulario("manga", "fruta gostos", "5.25")

        clicaNoBotaoDeSalvar()

        Espresso
            .onView(ViewMatchers.withText("manga"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    private fun clicaNoBotaoDeSalvar() {
        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_botao_salvar))
            .perform(ViewActions.click())
    }

    private fun preencheCamposDoFormulario(
        nome: String,
        descricao: String,
        valor: String
    ) {
        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_nome))
            .perform(
                ViewActions.replaceText(nome),
                ViewActions.closeSoftKeyboard()
            )

        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_descricao))
            .perform(
                ViewActions.replaceText(descricao),
                ViewActions.closeSoftKeyboard()
            )

        Espresso
            .onView(ViewMatchers.withId(R.id.activity_formulario_produto_valor))
            .perform(
                ViewActions.replaceText(valor),
                ViewActions.closeSoftKeyboard()
            )
    }

    private fun clicaNoFab() {
        Espresso
            .onView(ViewMatchers.withId(R.id.activity_lista_produtos_fab))
            .perform(ViewActions.click())
    }
}