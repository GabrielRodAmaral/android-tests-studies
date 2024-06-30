package br.com.alura.orgs

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import br.com.alura.orgs.database.AppDatabase
import br.com.alura.orgs.ui.activity.FormularioCadastroUsuarioActivity
import br.com.alura.orgs.ui.activity.LoginActivity
import org.junit.Before
import org.junit.Test

class LoginTests {

    @Before
    fun preparaAmbiente() {
        AppDatabase.instancia(InstrumentationRegistry.getInstrumentation().targetContext).clearAllTables()
    }

    @Test
    fun shouldBePossibleNavigateToRegisterScreenAndCreateANewUser() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.activity_login_botao_cadastrar))
            .perform(click())

        ActivityScenario.launch(FormularioCadastroUsuarioActivity::class.java)

        onView(withId(R.id.activity_formulario_cadastro_usuario))
            .perform(typeText("KotlinatorOfMetal"),
                ViewActions.closeSoftKeyboard()
            )

        onView(withId(R.id.activity_formulario_cadastro_email))
            .perform(typeText("kotlinator@gmail.com"),
                ViewActions.closeSoftKeyboard())

        onView(withId(R.id.activity_formulario_cadastro_senha))
            .perform(typeText("12345"),
                ViewActions.closeSoftKeyboard())

        onView(withId(R.id.activity_formulario_cadastro_botao_cadastrar))
            .perform(click())
    }

    @Test
    fun shouldBePossibleFillLoginFieldsAndClickTheLoginButton() {
        ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.activity_login_usuario))
            .perform(typeText("KotlinatorOfMetal"),
                ViewActions.closeSoftKeyboard())

        onView(withId(R.id.activity_login_senha))
            .perform(typeText("12345"),
                ViewActions.closeSoftKeyboard())

        onView(withId(R.id.activity_login_botao_entrar))
            .perform(click())
    }


}