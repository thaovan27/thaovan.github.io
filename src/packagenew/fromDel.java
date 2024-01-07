package packagenew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Vector;

public class fromDel implements ActionListener {
    JFrame JFDet,jfResutl;
    JLabel jlbid, jlbht, jlbl, jlbgt, jlbd, jlbgtF, jlbgtM;
    JTextField jtxfid, jtxtfht,  ltxtfd;
    JComboBox jcbbClass;
    JRadioButton jrbGenderF, jrbGenderM;
    JButton jbdel,jbtadd;
    boolean gender;
    JTable jtbStudent;
    DefaultTableModel dtm = new  DefaultTableModel();
    Vector tbHeader = new Vector();
    Vector tbContent = new Vector();
    static  MyUser ps= new MyUser();
    public void createUserInterFace(){
        int w = 500, h=200, x=10, y=10, d=20, we =120, he=30, yht=10;
        //Create JFrame
        JFDet = new JFrame();
//        JFDet.setTitle("Xoa sinh vien");
        JFDet.setTitle("Them sinh vien");
        JFDet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//tao nut x
        JFDet.setVisible(true);
        JFDet.setSize(w,h);
        JFDet.setResizable(false);//ko cho phep thay doi kthuoc
        JFDet.setLocationRelativeTo(null);//cho cua so ra giua
        JFDet.setLayout(null);
        //Create Component
        //msv
        //label
        jlbid = new JLabel();
        jlbid.setText("Mã sinh viên");
        jlbid.setBounds(x,y,we,he);
        JFDet.add(jlbid);

        //Gioi tinh
        jlbgt = new JLabel();
        jlbgt.setText("Giới tính");
        jlbgt.setBounds(x,y+x+d,we,he);
        JFDet.add(jlbgt);

        //textbox
        //msv
        jtxfid = new JTextField();
        jtxfid.setBounds(x+we+d,y,we,he);
        JFDet.add(jtxfid);

        jrbGenderM = new JRadioButton();
        jrbGenderM.setText("Nam");
        jrbGenderM.setBounds(x + we + d * 2, y +  x + d, we, he);
        JFDet.add(jrbGenderM);

        jrbGenderF = new JRadioButton();
        jrbGenderF.setText("Nữ");
        jrbGenderF.setBounds(x + 2 * we + d *2, y + x +  d, we, he);
        JFDet.add(jrbGenderF);

        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(jrbGenderM);
        bgGender.add(jrbGenderF);

        //button them
        jbdel = new JButton();
        jbdel.setText("Xóa");
        jbdel.setBounds(x+we+d,y +2*x+2*d,we,he);
        jbdel.addActionListener(this);
        JFDet.add(jbdel);

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
        try {
            boolean gender;
            if(jrbGenderF.isSelected()) gender = true;
            else gender = false;
            String id = jtxfid.getText()+"";
            ArrayList<User> lsst = ps.getAll();
            for (int i = 0; i < lsst.size(); i++) {
                User st = (User) lsst.get(i);
                Vector tbRow = new Vector();
                tbRow.add(st.getId());
                tbRow.add(st.getName());
                tbRow.add(st.getClassId());
                if (st.isGender()) {
                    tbRow.add("Female");
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
    String id; boolean isGender;
    @Override
    public void actionPerformed(ActionEvent e) {
        jbdel = (JButton)e.getSource();
        id =jtxfid.getText();
        boolean gender;
        if(jrbGenderF.isSelected()) gender = true;
        else gender = false;
        if(jtxfid.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Chua nhap ID");
        }
        else {
            if(ps.delStudent(id,gender))
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            else
                JOptionPane.showMessageDialog(null, "Xóa không thành công");
        }
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        showResults();
    }

    public static void main(String[] args) {
        fromDel jdel  = new fromDel();
        jdel.createUserInterFace();
    }
}