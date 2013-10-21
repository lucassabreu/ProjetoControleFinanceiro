package controlefinanceiro;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controlefinanceiro.dao.ConnectionUtil;
import controlefinanceiro.exception.LoginInvalidoRNException;
import controlefinanceiro.form.MainForm;

public class Main {
    public static void main(String[] args) throws LoginInvalidoRNException {

        ConnectionUtil.connectionType = ConnectionUtil.MY_SQL;

        // UsuarioControl.getInstance().validaLogin("lucas", "12345678");

        // nao de bola pra o try ... catch
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new MainForm();
    }
}
