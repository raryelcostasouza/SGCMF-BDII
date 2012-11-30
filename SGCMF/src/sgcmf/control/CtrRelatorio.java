/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sgcmf.control;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import sgcmf.model.other.AproveitamentoSelecao;
import sgcmf.model.other.ModelRelatorioSelecao;
import sgcmf.model.other.ResultadoGolsSelecao;
import sgcmf.view.relatorio.LimGerenciarRelatorio;

/**
 *
 * @author Helio
 */
public class CtrRelatorio
{
    private LimGerenciarRelatorio limGerenciarRelatorio;
    private CtrMain ctrMain;
    private CtrSelecao ctrSelecao;
    private CtrJogo ctrJogo;
    private CtrFalta ctrFalta;
    private CtrCartao ctrCartao;
    private CtrGol ctrGol;

    public CtrRelatorio(CtrMain ctrMain)
    {
        this.ctrMain = ctrMain;
        ctrSelecao = ctrMain.getCtrSelecao();
        ctrJogo = ctrMain.getCtrJogo();
        ctrFalta = ctrMain.getCtrFalta();
        ctrGol = ctrMain.getCtrGol();
        ctrCartao = ctrMain.getCtrCartao();
        limGerenciarRelatorio = new LimGerenciarRelatorio(this);
    }

    public void ativaTela()
    {
        limGerenciarRelatorio.setVisible(true);
    }

    public CtrMain getCtrMain()
    {
        return ctrMain;
    }

    public void gerarRelatorio(String stringIdSelecaoI, String stringIdSelecaoII)
    {
        ModelRelatorioSelecao modelRelatorioSelecaoI;
        ModelRelatorioSelecao modelRelatorioSelecaoII;
        //Dados Selecao I
        short idSelecaoI;
        String nomeSelecaoI;
        AproveitamentoSelecao objAproveitamentoSelecaoI;
        int qtdeFaltasI;
        int qtdeCartoesI;
        ResultadoGolsSelecao rgsI;

        //Dados Selecao II
        short idSelecaoII;
        String nomeSelecaoII;
        AproveitamentoSelecao objAproveitamentoSelecaoII;
        int qtdeFaltasII;
        int qtdeCartoesII;
        ResultadoGolsSelecao rgsII;

        idSelecaoI = Short.parseShort(stringIdSelecaoI);
        idSelecaoII = Short.parseShort(stringIdSelecaoII);

        nomeSelecaoI = ctrSelecao.pesquisarNomeSelecao(idSelecaoI);
        objAproveitamentoSelecaoI = ctrJogo.calculaNumVitoriasDerrotaEmpate(idSelecaoI);
        qtdeFaltasI = ctrFalta.calculaNumFaltas(idSelecaoI);
        qtdeCartoesI = ctrCartao.calculaNumCartoes(idSelecaoI);
        rgsI = ctrGol.calculaResultadoGolsSelecaoRelatorio(idSelecaoI);

        nomeSelecaoII = ctrSelecao.pesquisarNomeSelecao(idSelecaoII);
        objAproveitamentoSelecaoII = ctrJogo.calculaNumVitoriasDerrotaEmpate(idSelecaoII);
        qtdeFaltasII = ctrFalta.calculaNumFaltas(idSelecaoII);
        qtdeCartoesII = ctrCartao.calculaNumCartoes(idSelecaoII);
        rgsII = ctrGol.calculaResultadoGolsSelecaoRelatorio(idSelecaoII);

        modelRelatorioSelecaoI = new ModelRelatorioSelecao(nomeSelecaoI, objAproveitamentoSelecaoI,
                qtdeFaltasI, qtdeCartoesI, rgsI);

        modelRelatorioSelecaoII = new ModelRelatorioSelecao(nomeSelecaoII, objAproveitamentoSelecaoII,
                qtdeFaltasII, qtdeCartoesII, rgsII);

        montaRelatorioSelecao(modelRelatorioSelecaoI, modelRelatorioSelecaoII);
    }

    private void montaRelatorioSelecao(ModelRelatorioSelecao mrsI, ModelRelatorioSelecao mrsII)
    {
        ArrayList<ModelRelatorioSelecao> lista = new ArrayList<ModelRelatorioSelecao>();
        lista.add(mrsI);
        lista.add(mrsII);
        JRDataSource jrds = new JRBeanCollectionDataSource(lista);
        try
        {
            JasperReport jr = JasperCompileManager.compileReport("relatorio/RelatorioSelecao.jrxml");
            JasperPrint jp = JasperFillManager.fillReport(jr, null, jrds);
            JasperViewer.viewReport(jp, true);
        }
        catch (JRException ex)
        {
            Logger.getLogger(CtrRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    //Dados
//    private void preencheTextFields(Short idSelecao)
//    {
//        String nomeSelecao;
//        AproveitamentoSelecao objAproveitamentoSelecao;
//        int qtdeFaltas;
//        int qtdeCartoes;
//        ResultadoGolsSelecao rgs;
//        //Recebendo os campos
//        nomeSelecao = ctrSelecao.pesquisarNomeSelecao(idSelecao);
//        objAproveitamentoSelecao = ctrJogo.calculaNumVitoriasDerrotaEmpate(idSelecao);
//        qtdeFaltas = ctrFalta.calculaNumFaltas(idSelecao);
//        qtdeCartoes = ctrCartao.calculaNumCartoes(idSelecao);
//        rgs = ctrGol.calculaResultadoGolsSelecaoRelatorio(idSelecao);
//        //Atulizando os TextFields
////        jtfNomeSelecao.setText(nomeSelecao);
////        jtfJogosDisputados.setText(objAproveitamentoSelecao.getJogosDisputados() + "");
////        jtfVitorias.setText(objAproveitamentoSelecao.getVitorias() + "");
////        jtfDerrotas.setText(objAproveitamentoSelecao.getDerrotas() + "");
////        jtfEmpates.setText(objAproveitamentoSelecao.getEmpates() + "");
////        jtfAproveitamento.setText(objAproveitamentoSelecao.getAproveitamento() + "%");
////        jtfFaltas.setText(qtdeFaltas + "");
////        jtfCartoes.setText(qtdeCartoes + "");
////        jtfGolsPro.setText(rgs.getNumGolsMarcados() + "");
////        jtfGolsContra.setText(rgs.getNumGolsSofridos() + "");
////        jtfSaldoGols.setText(rgs.getSaldoGols() + "");
//    }
}
