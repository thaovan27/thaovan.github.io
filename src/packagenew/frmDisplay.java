package packagenew;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class frmDisplay implements ActionListener{
    JFrame jDipl, jResult;
    
    JTable JtbDispl;

    JButton jbtnDis;
    DefaultTableModel dtm = new  DefaultTableModel();
    Vector tbHeader = new Vector();
    Vector tbContent = new Vector();
    MyUser ps=new MyUser();

    public void creatUserInterFace() {
        int w = 430, h = 380, x = 10, y = 20, d = 20, we = 110, he = 30;
        jDipl = new JFrame();
        jDipl.setTitle("Thêm mới sinh viên");
        jDipl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jDipl.setVisible(true);
        jDipl.setSize(w, h);
        jDipl.setResizable(true);
        jDipl.setLocationRelativeTo(null);
        jDipl.setLayout(null);

        jbtnDis = new JButton();
        jbtnDis.setText("Hien Thi");
        jbtnDis.setBounds(x, y, we, he);
        jbtnDis.addActionListener( this);
        jDipl.add(jbtnDis);
    }
    
    public void showResults(){
        jResult = new JFrame("Kết quả tìm kiếm theo lớp");
        jResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jResult.setVisible(true);
        jResult.setLocationRelativeTo(null);
        jResult.setSize(400,300);

        tbHeader.add("Mã Sinh viên");
        tbHeader.add("Họ tên");
        tbHeader.add("Lớp");
        tbHeader.add("Giới tính");
        tbHeader.add("Điểm");

        JtbDispl = new JTable();
        JtbDispl.setModel(dtm);
        JtbDispl.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JtbDispl.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(JtbDispl);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        try{
            ArrayList<User> lst = ps.getAll();
            for(User st : lst){
                Vector tbRow = new Vector();
                tbRow.add(st.getId());
                tbRow.add(st.getName());
                tbRow.add(st.getClassId());
                if(st.isGender()){
                    tbRow.add("Male");
                }else {
                    tbRow.add("Female");
                }
                tbRow.add(st.getMark());
                tbContent.add(tbRow);
            }
            dtm.setDataVector(tbContent,tbHeader); //cap nhap du lieu vao bang
            JtbDispl.setModel(dtm);//gan model moi chua du lieu vao bang
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }jResult.add(scroll);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jbtnDis= (JButton)e.getSource();
        dtm.setRowCount(0);
        dtm.setColumnCount(0);
        showResults();
    }
    public static void main(String[] args) {
        frmDisplay fu =  new frmDisplay();
        fu.creatUserInterFace();
    }
}
