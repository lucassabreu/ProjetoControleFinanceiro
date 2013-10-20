package controlefinanceiro;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import controlefinanceiro.control.UsuarioControl;
import controlefinanceiro.dao.ConnectionUtil;
import controlefinanceiro.exception.LoginInvalidoRNException;
import controlefinanceiro.form.RelatorioForm;

public class Main {
    public static void main(String[] args) throws LoginInvalidoRNException {

        ConnectionUtil.connectionType = ConnectionUtil.MY_SQL;

        UsuarioControl.getInstance().validaLogin("lucas", "lucas");

        // nao de bola pra o try ... catch try {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RelatorioForm();
        // new MainForm();
    }
}
