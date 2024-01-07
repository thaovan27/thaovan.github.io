package packagenew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;


public class frmUpdate implements ActionListener {
    JFrame JFUpdate, jfResutl;
    JTable jtbStudent;
    JLabel jlbID, jlbName, jlbClassID, jlbGender,jlbMark ;
    JTextField jtfID, jtfName, jtfMark;
    JComboBox jcbClass;
    JButton jbUpdate, jbFind;
    JRadioButton jrbGenderM, jrbGenderF;
    DefaultTableModel dtm = new  DefaultTableModel();
    Vector tbHeader = new Vector();
    Vector tbContent = new Vector();
    MyUser ps=new MyUser();

    public void creatUserInterFace(){
        int w = 430, h = 380, x = 10, y = 20, d = 20, we = 110, he = 30;
        JFUpdate = new JFrame();
        JFUpdate.setTitle("Thêm mới sinh viên");
        JFUpdate.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFUpdate.setVisible(true);
        JFUpdate.setSize(w, h);
        JFUpdate.setResizable(true);
        JFUpdate.setLocationRelativeTo(null);
        JFUpdate.setLayout(null);

        //id
        jlbID = new JLabel();
        jlbID.setText("Mã sinh viên: ");
        jlbID.setBounds(x, y , we, he);
        JFUpdate.add(jlbID);

        //ht
        jlbName = new JLabel();
        jlbName.setText("Họ tên: ");
        jlbName.setBounds(x, y +  2*x +2*d, we, he);
        JFUpdate.add(jlbName);

        //gender
        jlbGender = new JLabel();
        jlbGender.setText("Giới tính");
        jlbGender.setBounds(x, y + 4 * x + 4 * d, we, he);
        JFUpdate.add(jlbGender);

        //class
        jlbClassID = new JLabel();
        jlbClassID.setText("Lớp: ");
        jlbClassID.setBounds(x, y + 6 * x + 6 * d, we, he);
        JFUpdate.add(jlbClassID);

        //điểm
        jlbMark = new JLabel();
        jlbMark.setText("Điểm: ");
        jlbMark.setBounds(x, y + 8 * x +8* d, we, he);
        JFUpdate.add(jlbMark);

        //textbox
        //id
        jtfID = new JTextField();
        jtfID.setBounds(x + we + d, y , we, he);
        JFUpdate.add(jtfID);
        //ht
        jtfName = new JTextField();
        jtfName.setBounds(x + we + d, y +2* x + 2 * d, we, he);
        JFUpdate.add(jtfName);

        //gender
        jrbGenderM = new JRadioButton();
        jrbGenderM.setText("Nam");
        jrbGenderM.setBounds(x + we + d * 2, y + 4 * x + 4 * d, we, he);
        JFUpdate.add(jrbGenderM);

        jrbGenderF = new JRadioButton();
        jrbGenderF.setText("Nữ");
        jrbGenderF.setBounds(x + 2 * we + d *2, y + 4 * x + 4 * d, we, he);
        JFUpdate.add(jrbGenderF);

        //class
        jcbClass = new JComboBox<>();
        ArrayList<String> lstClass = ps.getclassId();
        for(int i=0; i< lstClass.size(); i++){
            jcbClass.addItem((lstClass.get(i)));
        }
        jcbClass.setBounds(x + we + d, y + 6 * x + 6 * d, we, he);
        JFUpdate.add(jcbClass);


        //điểm
        jtfMark = new JTextField();
        jtfMark.setBounds(x + we + d, y + 8 * x + 8 * d, we, he);
        JFUpdate.add(jtfMark);

        //button
        jbUpdate = new JButton();
        jbUpdate.setText("Update");
        jbUpdate.setEnabled(true);
        jbUpdate.setBounds(x + 2*we + 2*d, y + x , we, he);
        jbUpdate.addActionListener(this);
        JFUpdate.add(jbUpdate);

        jbFind = new JButton();
        jbFind.setText("Tim kiem");
        jbUpdate.setEnabled(false);
        jbFind.setBounds(x + 2*we + 2*d, y + 2*x + 2* d, we, he);
        jbFind.addActionListener(this);
        JFUpdate.add(jbFind);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbUpdate) {
            jbUpdate.setEnabled(true);
            String id = jtfID.getText();
            String name = jtfName.getText();
            String classId = jcbClass.getSelectedItem()+"";
            boolean gender = jrbGenderF.isSelected(); // Sử dụng isSelected() trực tiếp cho RadioButton
            double mark = Double.parseDouble(jtfMark.getText());

            if (ps.updateStudent(id, name, classId, gender, mark)) {
                JOptionPane.showMessageDialog(JFUpdate, "Cập nhật thành công");
            } else {
                JOptionPane.showMessageDialog(JFUpdate, "Cập nhật không thành công");
            }
        } else if (e.getSource() == jbFind) {
            String id = jtfID.getText();
            jbUpdate.setEnabled(true);
            User s = ps.getbyId(id);
            if (s != null) {
                jtfName.setText(s.getName());
                jcbClass.setSelectedItem(s.getClassId());
                jtfMark.setText(s.getMark()+" ");
                if(s.isGender()){
                    jrbGenderF.setSelected(true);
                }
                else jrbGenderM.setSelected(true);

                jrbGenderM.setSelected(!s.isGender());
            } else {
                JOptionPane.showMessageDialog(JFUpdate, "Không tìm thấy sinh viên");
            }
        }

        // Gọi lại phương thức showResults để cập nhật dữ liệu sau khi thực hiện thao tác
        jbFind =(JButton) e.getSource();
        //xoa dong cot chuan bi cho du lieu moi
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        showResults();
    }

    private void showResults(){
        int w = 430, h = 380, x = 10, y = 20, d = 20, we = 110, he = 30;
        jfResutl = new JFrame("Kết quả tìm kiếm theo lớp");
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
            String classID = jcbClass.getSelectedItem()+"";
            boolean gender;
            if(jrbGenderF.isSelected()) gender = true;
            else gender = false;
            String id = jtfID.getText()+"";
            User lsst = ps.getbyId(id);
            Vector tbRow = new Vector();
            tbRow.add(lsst.getId());
            tbRow.add(lsst.getName());
            tbRow.add(lsst.getClassId());
            if(lsst.isGender()){
                tbRow.add("Male");
            }else tbRow.add("Female");
            tbRow.add(lsst.getMark());
            tbContent.add(tbRow);
            dtm.setDataVector(tbContent,tbHeader);
            jtbStudent.setModel(dtm);

        }catch (Exception ex){
            JOptionPane.showInternalMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);

        }
        jfResutl.add(scroll);
    }
    public static void main(String[] args) {
        frmUpdate fu =  new frmUpdate();
        fu.creatUserInterFace();
    }

}
