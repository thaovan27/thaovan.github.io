package packagenew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class fromSearchbyClassGender implements ActionListener {
    JFrame JFSClassGender, jfResult;
    JTable jtbStudent;
    JLabel jlbClassID, jlbGender;
    JRadioButton jrGender1, jrGender2;
    JComboBox<String> jcClassID;
    JButton JbSearch;
    DefaultTableModel dtm = new DefaultTableModel();
    Vector tbHeader = new Vector();
    Vector tbContent = new Vector();

    static MyUser ps = new MyUser();

    public void createUserInterface() {
        int w = 500, h = 150, x = 20, y = 15, d = 20, we = 120, he = 20;

        JFSClassGender = new JFrame();
        JFSClassGender.setTitle("Tìm kếm theo lớp và giới tính");
        JFSClassGender.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFSClassGender.setVisible(true);
        JFSClassGender.setSize(w, h);
        JFSClassGender.setResizable(false); // không cho phép thay đổi kích thước
        JFSClassGender.setLocationRelativeTo(null);
        JFSClassGender.setLayout(null);

        jlbClassID = new JLabel();
        jlbClassID.setText("Chọn Lớp");
        jlbClassID.setBounds(x, y, we, he);
        JFSClassGender.add(jlbClassID);

        jlbGender = new JLabel();
        jlbGender.setText("Giới tính");
        jlbGender.setBounds(x, y +  x + d, we, he);
        JFSClassGender.add(jlbGender);

        jcClassID = new JComboBox<>();

        ArrayList<String> st = ps.getclassId();

        for (int i = 0; i < st.size(); i++) {
            jcClassID.addItem(st.get(i));
        }
        jcClassID.setBounds(x + we + d, y , we, he);
        JFSClassGender.add(jcClassID);

        jrGender1 = new JRadioButton();
        jrGender1.setText("Male");
        jrGender1.setBounds(x + we + d,y+ he + d,we,he);
        JFSClassGender.add(jrGender1);

        jrGender2 = new JRadioButton();
        jrGender2.setText("Female");
        jrGender2.setBounds(x + we*2 + d,y+ he + d,we,he);
        JFSClassGender.add(jrGender2);

        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(jrGender1);
        bgGender.add(jrGender2);

        JbSearch = new JButton();
        JbSearch.setText("Tìm kiếm");
        JbSearch.setBounds(x + we*2 + d*2, y , we, he);
        JbSearch.addActionListener(this);
        JFSClassGender.add(JbSearch);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JbSearch= (JButton)e.getSource();
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        showResults();
    }

    private void showResults() {
        jfResult = new JFrame("Kết quả tìm kiếm theo lớp");
        jfResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jfResult.setVisible(true);
        jfResult.setLocationRelativeTo(null);
        jfResult.setSize(400,300);

        tbHeader.add("Mã Sinh viên");
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
        try{
            String classID = jcClassID.getSelectedItem()+"";
            boolean gender;
            if(jrGender1.isSelected()) gender = true;
            else gender = false;
            ArrayList<User> lst = ps.getStudents(classID,gender);
            for(int i = 0; i< lst.size(); i++){
                User s = (User) lst.get(i);
                Vector tbRow = new Vector();
                tbRow.add(s.getId());
                tbRow.add(s.getName());
                tbRow.add(s.getClassId());
                if(s.isGender()){
                    tbRow.add("Male");
                }else {
                    tbRow.add("Female");
                }
                tbRow.add(s.getMark());
                tbContent.add(tbRow);
            }
            dtm.setDataVector(tbContent,tbHeader);
            jtbStudent.setModel(dtm);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        jfResult.add(scroll);
    }

    public static void main(String[] args) {
        fromSearchbyClassGender src = new fromSearchbyClassGender();
        src.createUserInterface();
    }
}
