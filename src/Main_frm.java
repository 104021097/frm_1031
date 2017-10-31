import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Main_frm extends JFrame {
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
    private JMenu jmS = new JMenu("Set");
    private JMenu jmG = new JMenu("Game");
    private JMenu jmA = new JMenu("About");
//Calculator-----------------------------------------------------------------------------------------------------------
    private JPanel jpnCal = new JPanel(new GridLayout(4,4));
    private JTextField jtf = new JTextField();
    private  JButton jbtnCal[] = new JButton[10];
    private int data_cal[] = new int[10];
    private Random rnd_Cal = new Random(System.currentTimeMillis());
//---------------------------------------------------------------------------------------------------------------------
    private JDesktopPane jdp = new JDesktopPane();
    private JMenuItem jMenuItemFExit = new JMenuItem("Exit");
    private JMenuItem jMenuItemFLotto = new JMenuItem("Lotto");
    private JMenuItem jMenuItemCalculator = new JMenuItem("Calculator");
    private JMenuItem jMenuItemFont = new JMenuItem("Font");
    private JMenuItem jMenuItemAddCategory = new JMenuItem("Add Category");

    private JInternalFrame jInternalFrame = new JInternalFrame();
    private JInternalFrame jInternalFrame_cal = new JInternalFrame();
    private Container jifCP;
    private JPanel jpn = new JPanel(new GridLayout(1,6,5,5));
    private JPanel jpn1 = new JPanel(new GridLayout(1,2,5,5));

    private JLabel jlbs[] = new JLabel[6];
    private int data[] = new int[6];
    private Random rnd = new Random(System.currentTimeMillis());


    private JButton jbtnClose = new JButton("Close");
    private JButton jbtnRegen = new JButton("Generate");
    private JButton jbtnClose_Cal = new JButton("Close");
    private JButton jbtnRegen_Cal = new JButton("Generate");
//20171031 Font--------------------------------------------------------------------------------------------------------
    private JPanel jpanel1 = new JPanel(new GridLayout(2,3,5,5));
    private JLabel jlbFontFamily = new JLabel("Family");
    private JLabel jlbFontStyle = new JLabel("Style");
    private JLabel jlbFontSize = new JLabel("Size");
    private JTextField jtfFamily = new JTextField("Times New Romans");
    //private JTextField jtfStyle = new JTextField("PLAIN");
    private JTextField jtfSize = new JTextField("20");
    private String[] options = {"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcbFStyle = new JComboBox(options);
//20171031 Add Category-------------------------------------------------------------------------------------------------
    private  JInternalFrame jIfAddCategory = new JInternalFrame();
    private Container jIFAddCategoryCP;
    private JMenuBar jIFAddCategoryJmb = new JMenuBar();
    private JMenu jmData = new JMenu("Data");
    private JMenuItem jmiDataLoad = new JMenuItem("Load");
    private JMenuItem jmiDataNew = new JMenuItem("New");
    private JMenuItem jmiDataClose = new JMenuItem("Close");
    private JFileChooser jfc = new JFileChooser();
    private JTextArea jta = new JTextArea();
    private JScrollPane jsp1 = new JScrollPane(jta);
 //---------------------------------------------------------------------------------------------------------------------
    private int frmW = 500, frmH = 450;
    private Login_frm loginFrm;
    public Main_frm(Login_frm frm){
        loginFrm = frm;
        initComp();
    }
    private void initComp(){
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        jInternalFrame.setBounds(0,0,200,100);
        jInternalFrame_cal.setBounds(0,0,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);

        jmF.add(jMenuItemFExit);
        jmG.add(jMenuItemFLotto);
        jmG.add(jMenuItemCalculator);
        jmS.add(jMenuItemFont);
        jmb.add(jmF);
        jmb.add(jmS);
        jmb.add(jmG);
        jmb.add(jmA);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrm.reset();
                loginFrm.setVisible(true);
            }
        });

        jpn1.add(jbtnClose);
        jpn1.add(jbtnRegen);
        jpnCal.add(jbtnClose_Cal);
        jpnCal.add(jbtnRegen_Cal);
        jpnCal.add(jtf);
        jtf.setEditable(false);

        jpanel1.add(jlbFontFamily);
        jpanel1.add(jlbFontStyle);
        jpanel1.add(jlbFontSize);
        jpanel1.add(jtfFamily);
        jpanel1.add(jcbFStyle);
        //jpanel1.add(jtfStyle);
        jpanel1.add(jtfSize);
        this.setTitle("Main Frame");
 //Add Category---------------------------------------------------------------------------------------------------------
 jMenuItemAddCategory.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {
         jIfAddCategory.setVisible(true);
     }
 });
 jIFAddCategoryCP = jIfAddCategory.getContentPane();
 jIFAddCategoryCP.setLayout(new BorderLayout(5,5));
 jIFAddCategoryCP.add(jsp1,BorderLayout.CENTER);
 jIfAddCategory.setJMenuBar(jIFAddCategoryJmb);
 jIfAddCategory.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

//Menu------------------------------------------------------------------------------------------------------------------
        jMenuItemFExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        jMenuItemFLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame);
                jInternalFrame.setVisible(true);
            }
        });
        jMenuItemCalculator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jInternalFrame_cal);
                jInternalFrame_cal.setVisible(true);
            }
        });
//Menu Button-----------------------------------------------------------------------------------------------------------

        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame.dispose();
            }
        });
        jbtnRegen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lottoGenerate();
            }
        });
        jbtnClose_Cal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jInternalFrame_cal.dispose();
            }
        });
//20171031-------------------------------------------------------------------------------------------------------------
        jMenuItemFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(Main_frm.this,jpanel1,"Font setting",
                        JOptionPane.OK_CANCEL_OPTION);
                int fontStyle = 0;
                switch (jcbFStyle.getSelectedIndex()){
                    case 0:
                        fontStyle = Font.PLAIN;
                        break;
                    case 1:
                        fontStyle = Font.BOLD;
                        break;
                    case 2:
                        fontStyle = Font.ITALIC;
                        break;
                    case  3:
                        fontStyle = Font.BOLD + Font.ITALIC;
                        break;
                }
                if(result == JOptionPane.OK_OPTION){
                    UIManager.put("Menu.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())));
                    UIManager.put("MenuItem.font",new Font(jtfFamily.getText(),fontStyle,Integer.parseInt(jtfSize.getText())));
                }

            }
        });
//---------------------------------------------------------------------------------------------------------------------
        jifCP = jInternalFrame.getContentPane();
        jifCP.setLayout(new BorderLayout(5,5));
        jifCP.add(jpn, BorderLayout.CENTER);
        jifCP.add(jpn1, BorderLayout.SOUTH);
//20171024 Lotto-------------------------------------------------------------------------------------------------------
        for(int i=0;i<6;i++){
            jlbs[i] = new JLabel();
            jlbs[i].setFont(new Font(null, Font.PLAIN,22));
            jlbs[i].setOpaque(true);
            jlbs[i].setBackground(new Color(186,85,211));
            jlbs[i].setHorizontalAlignment(JLabel.CENTER);
            jpn.add(jlbs[i]);

        }
        lottoGenerate();
        jMenuItemFLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lottoGenerate();

            }
        });
//---------------------------------------------------------------------------------------------------------------------



        jMenuItemFExit.setAccelerator(KeyStroke.getKeyStroke('X',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    }


    private  void lottoGenerate(){
        int i =0;
        while(i<6){
            data[i] = rnd.nextInt(42)+1;
            int j =0;
            boolean flag = true;
            while (j<i && flag){
                if(data[i] == data[j]){
                    flag = false;
                }
                j++;
            }
            if(flag){
                jlbs[i].setText(Integer.toString(data[i]));
                i++;
            }
        }
    }



}
