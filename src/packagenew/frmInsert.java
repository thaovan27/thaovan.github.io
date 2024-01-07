package packagenew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class frmInsert implements ActionListener {
    JFrame JFInsert, jfResutl;
    JTable jtbStudent;
    JLabel jlbID, jlbName, jlbClassID, jlbGender,jlbMark ;
    JTextField jtfID, jtfName, jtfMark;
    JComboBox jcbClass;
    JButton jbInsert;
    JRadioButton jrbGenderM, jrbGenderF;
    DefaultTableModel dtm = new  DefaultTableModel();
    Vector tbHeader = new Vector();
    Vector tbContent = new Vector();
    MyUser ps=new MyUser();

    public void creatUserInterFace(){
        int w = 430, h = 380, x = 10, y = 20, d = 20, we = 110, he = 30;
        JFInsert = new JFrame();
        JFInsert.setTitle("Thêm mới sinh viên");
        JFInsert.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFInsert.setVisible(true);
        JFInsert.setSize(w, h);
        JFInsert.setResizable(true);
        JFInsert.setLocationRelativeTo(null);
        JFInsert.setLayout(null);

        //id
        jlbID = new JLabel();
        jlbID.setText("Mã sinh viên: ");
        jlbID.setBounds(x, y , we, he);
        JFInsert.add(jlbID);

        //ht
        jlbName = new JLabel();
        jlbName.setText("Họ tên: ");
        jlbName.setBounds(x, y +  2*x +2*d, we, he);
        JFInsert.add(jlbName);

        //gender
        jlbGender = new JLabel();
        jlbGender.setText("Giới tính");
        jlbGender.setBounds(x, y + 4 * x + 4 * d, we, he);
        JFInsert.add(jlbGender);

        //class
        jlbClassID = new JLabel();
        jlbClassID.setText("Lớp: ");
        jlbClassID.setBounds(x, y + 6 * x + 6 * d, we, he);
        JFInsert.add(jlbClassID);

        //điểm
        jlbMark = new JLabel();
        jlbMark.setText("Điểm: ");
        jlbMark.setBounds(x, y + 8 * x +8* d, we, he);
        JFInsert.add(jlbMark);

        //textbox
        //id
        jtfID = new JTextField();
        jtfID.setBounds(x + we + d, y , we, he);
        JFInsert.add(jtfID);
        //ht
        jtfName = new JTextField();
        jtfName.setBounds(x + we + d, y +2* x + 2 * d, we, he);
        JFInsert.add(jtfName);

        //gender
        jrbGenderM = new JRadioButton();
        jrbGenderM.setText("Nam");
        jrbGenderM.setBounds(x + we + d * 2, y + 4 * x + 4 * d, we, he);
        JFInsert.add(jrbGenderM);

        jrbGenderF = new JRadioButton();
        jrbGenderF.setText("Nữ");
        jrbGenderF.setBounds(x + 2 * we + d *2, y + 4 * x + 4 * d, we, he);
        JFInsert.add(jrbGenderF);

        //class
        jcbClass = new JComboBox<>();
        ArrayList<String> lstClass = ps.getclassId();
        for(int i=0; i< lstClass.size(); i++){
            jcbClass.addItem((lstClass.get(i)));
        }
        jcbClass.setBounds(x + we + d, y + 6 * x + 6 * d, we, he);
        JFInsert.add(jcbClass);


        //điểm
        jtfMark = new JTextField();
        jtfMark.setBounds(x + we + d, y + 8 * x + 8 * d, we, he);
        JFInsert.add(jtfMark);

        //button
        jbInsert = new JButton();
        jbInsert.setText("Insert");
        jbInsert.setEnabled(true);
        jbInsert.setBounds(x + 2*we + 2*d, y + x , we, he);
        jbInsert.addActionListener(this);
        JFInsert.add(jbInsert);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            jbInsert = (JButton)e.getSource();
            String id  =jtfID.getText();
            String name= jtfName.getText();
            boolean gender;
            if(jrbGenderF.isSelected()) gender = true;
            else gender = false;
            String classID = jcbClass.getSelectedItem()+" ";
            double mark = Double.parseDouble(jtfMark.getText());
            if(ps.checkFound(id)){
                JOptionPane.showMessageDialog(null, "ID đã tồn tại. Vui lòng nhập ID khác.");
            }else {
                if (ps.insertStudent(id, name, classID, gender, mark)) {
                    JOptionPane.showMessageDialog(null, "Thêm mới sinh viên thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm mới sinh viên không thành công");
                }
            }
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            showResults();
    }
    private void showResults(){
        int w = 430, h = 380, x = 10, y = 20, d = 20, we = 110, he = 30;
        jfResutl = new JFrame("Kết quả khi them lop");
        jfResutl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfResutl.setVisible(true);
        jfResutl.setSize(w, h);

        tbHeader.add("Mã sinh viên");
        tbHeader.add("Họ tên");
        tbHeader.add("Lớp");
        tbHeader.add("Giới tính");
        tbHeader.add("Điểm");

        jtbStudent = new JTable();
        jtbStudent.setModel(dtm);
        jtbStudent.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtbStudent.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(jtbStudent);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try {
            //lay thong tin tu giao dien nguoi dung
            String classID = jcbClass.getSelectedItem()+"";
            String name = jtfName.getText()+" ";
            boolean gender;
            if(jrbGenderF.isSelected()) gender = true;
            else gender = false;
            String id = jtfID.getText()+"";
            double mark = Double.parseDouble(jtfMark.getText());
            //them vao CSDL
            ArrayList<User> lsst = ps.getAll();
            for (int i = 0; i < lsst.size(); i++) {
                User st = (User) lsst.get(i);
                Vector tbRow = new Vector();
                tbRow.add(st.getId());
                tbRow.add(st.getName());
                tbRow.add(st.getClassId());
                if (st.isGender()) {
                    tbRow.add("FeMale");
                } else tbRow.add("male");
                tbRow.add(st.getMark());
                tbContent.add(tbRow);
            }
            dtm.setDataVector(tbContent, tbHeader);
            jtbStudent.setModel(dtm);
        }catch (Exception ex){
            JOptionPane.showInternalMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

        }
        jfResutl.add(scroll);
    }

    public static void main(String[] args) {
        frmInsert fu =  new frmInsert();
        fu.creatUserInterFace();
    }
}
