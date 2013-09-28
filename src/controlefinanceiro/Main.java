package controlefinanceiro;

import javax.swing.UIManager;

import controlefinanceiro.dao.Categoria;
import controlefinanceiro.dao.Natureza;
import controlefinanceiro.form.CategoriaForm;
import controlefinanceiro.form.CategoriaSimplesForm;
import controlefinanceiro.form.UsuarioLoginForm;
import controlefinanceiro.form.UsuarioRegistroForm;

public class Main {
    public static void main(String[] args) {

        // nao de bola pra o try ... catch
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            //UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (Exception e) {

        }

        new UsuarioLoginForm();
        new UsuarioRegistroForm();
        new CategoriaSimplesForm(new Categoria(null, 4544, "Bananas", Natureza.DESPESA), true);
        new CategoriaForm();
    }
}
