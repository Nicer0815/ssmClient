package client;

import database.ReaderInfo;
import database.StaffInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ClientUI {

    private JFrame clientFrame;
    private JTable readerTable;
    private JTable staffTable;
    //private JScrollPane

    private JButton newReader;
    private JButton newStaff;
    private JButton updateReader;
    private JButton updateStaff;

    private JPanel readerPanel;
    private JPanel staffPanel;
    private JPanel operatePanel;
    
    private String reader[][] = null;
    private String staff[][] = null;

    ClientUI(){
        Init();
    }
    public void Init(){
        clientFrame = new JFrame("图书馆人事管理系统");
        clientFrame.setBounds(100, 100, 960, 787);
        clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        //clientFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        createReaderPanel();
        createStaffPanel();
        createOperation();
        refreshData();
        clientFrame.add(readerPanel,BorderLayout.WEST);
        clientFrame.add(staffPanel,BorderLayout.EAST);
        clientFrame.add(operatePanel,BorderLayout.NORTH);
        clientFrame.setVisible(true);
    }

    public void createReaderPanel(){
        readerPanel = new JPanel(new BorderLayout());
        int size = importReaderData();
        readerTable = new JTable(size,4);
        readerTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        readerTable.setModel(new DefaultTableModel(reader,new String[] {
                "ReaderID", "Name","password","state"
        }));
        JScrollPane readerScrollPane = new JScrollPane();
        readerPanel.add(readerScrollPane,BorderLayout.CENTER);
        readerScrollPane.setViewportView(readerTable);
        readerPanel.setVisible(true);

    }
    public void createStaffPanel(){
        staffPanel = new JPanel(new BorderLayout());
        int size = importStaffData();
        staffTable = new JTable(size,4);
        staffTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        staffTable.setModel(new DefaultTableModel(staff,new String[] {
                "JobID", "PersonID","Name","PhoneNum"
        }));
        JScrollPane staffScrollPane = new JScrollPane();
        staffPanel.add(staffScrollPane,BorderLayout.CENTER);
        staffScrollPane.setViewportView(staffTable);
        staffPanel.setVisible(true);

    }
    public void createOperation(){
        //operatePanel = new JPanel(new GridLayout(15,1));
        operatePanel = new JPanel(new FlowLayout());
        newReader = new JButton("新增读者");
        newStaff = new JButton("新增员工");
        updateReader = new JButton("更新读者信息");
        updateStaff = new JButton("更新员工信息");

        newReader.setFont(new Font("宋体",Font.BOLD,20));
        newStaff.setFont(new Font("宋体",Font.BOLD,20));
        updateReader.setFont(new Font("宋体",Font.BOLD,20));
        updateStaff.setFont(new Font("宋体",Font.BOLD,20));


        operatePanel.add(newReader);
        operatePanel.add(newStaff);
        operatePanel.add(updateReader);
        operatePanel.add(updateStaff);

        newReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame jFrame = new JFrame("新增读者");
                jFrame.setBounds(500,500,400,420);
                jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
                //jFrame.setLayout(new GridLayout(10,1));

                Font ft = new Font("宋体",Font.BOLD,15);
                JLabel jl1 = new JLabel("readerId:");
                JLabel jl2 = new JLabel("Name:");
                JLabel jl3 = new JLabel("sex:");
                JLabel jl4 = new JLabel("phoneNum:");
                JLabel jl5 = new JLabel("password:");
                jl1.setFont(ft);
                jl2.setFont(ft);
                jl3.setFont(ft);
                jl4.setFont(ft);
                jl5.setFont(ft);
                final JTextField jt1 = new JTextField(15);
                final JTextField jt2 = new JTextField(15);
                final JTextField jt3 = new JTextField(15);
                final JTextField jt4 = new JTextField(15);
                final JTextField jt5 = new JTextField(15);
                JButton tjb = new JButton("确认");
                //readerId,Name,sex,phoneNum,password
                jFrame.add(jl1);
                jFrame.add(jt1);
                jFrame.add(jl2);
                jFrame.add(jt2);
                jFrame.add(jl3);
                jFrame.add(jt3);
                jFrame.add(jl4);
                jFrame.add(jt4);
                jFrame.add(jl5);
                jFrame.add(jt5);
                jFrame.add(tjb);

                jFrame.setVisible(true);
                tjb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ReaderInfo readerInfo = new ReaderInfo();
                        //readerId,Name,sex,phoneNum,password
                        Map<String,String>map = new HashMap<>();
                        map.put("readerId",jt1.getText());
                        map.put("Name",jt2.getText());
                        map.put("sex",jt3.getText());
                        map.put("phoneNum",jt4.getText());
                        map.put("password",jt5.getText());
                        readerInfo.addReader(map);
                        refreshData();
                        jFrame.setVisible(false);
                    }
                });

            }
        });
        newStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFrame jFrame = new JFrame("新增员工");
                jFrame.setBounds(500,500,400,460);
                jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
                //jFrame.setLayout(new GridLayout(10,1));

                Font ft = new Font("宋体",Font.BOLD,15);
                JLabel jl1 = new JLabel("jobId:");
                JLabel jl2 = new JLabel("personId:");
                JLabel jl3 = new JLabel("Name:");
                JLabel jl4 = new JLabel("password:");
                JLabel jl5 = new JLabel("sex:");
                JLabel jl6 = new JLabel("phoneNum:");
                jl1.setFont(ft);
                jl2.setFont(ft);
                jl3.setFont(ft);
                jl4.setFont(ft);
                jl5.setFont(ft);
                jl6.setFont(ft);
                final JTextField jt1 = new JTextField(15);
                final JTextField jt2 = new JTextField(15);
                final JTextField jt3 = new JTextField(15);
                final JTextField jt4 = new JTextField(15);
                final JTextField jt5 = new JTextField(15);
                final JTextField jt6 = new JTextField(15);
                JButton tjb = new JButton("确认");
                //jobId,personId,Name,password,sex,phoneNum
                jFrame.add(jl1);
                jFrame.add(jt1);
                jFrame.add(jl2);
                jFrame.add(jt2);
                jFrame.add(jl3);
                jFrame.add(jt3);
                jFrame.add(jl4);
                jFrame.add(jt4);
                jFrame.add(jl5);
                jFrame.add(jt5);
                jFrame.add(jl6);
                jFrame.add(jt6);
                jFrame.add(tjb);

                jFrame.setVisible(true);
                tjb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        StaffInfo staffInfo = new StaffInfo();
                        //jobId,personId,Name,password,sex,phoneNum
                        Map<String,String>map = new HashMap<>();
                        map.put("jobId",jt1.getText());
                        map.put("personId",jt2.getText());
                        map.put("Name",jt3.getText());
                        map.put("password",jt4.getText());
                        map.put("sex",jt5.getText());
                        map.put("phoneNum",jt6.getText());
                        staffInfo.addStaff(map);
                        refreshData();
                        jFrame.setVisible(false);
                    }
                });

            }
        });

        updateReader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String readerId,String Name, String password, String state
                final JFrame jFrame = new JFrame("更新读者信息");
                jFrame.setBounds(500,500,400,380);
                jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
                //jFrame.setLayout(new GridLayout(10,1));

                Font ft = new Font("宋体",Font.BOLD,15);
                JLabel jl1 = new JLabel("readerId:");
                JLabel jl2 = new JLabel("Name:");
                JLabel jl3 = new JLabel("password:");
                JLabel jl4 = new JLabel("state:");
                jl1.setFont(ft);
                jl2.setFont(ft);
                jl3.setFont(ft);
                jl4.setFont(ft);
                final JTextField jt1 = new JTextField(15);
                final JTextField jt2 = new JTextField(15);
                final JTextField jt3 = new JTextField(15);
                final JTextField jt4 = new JTextField(15);
                JButton tjb = new JButton("确认");
                //String readerId,String Name, String password, String state
                jFrame.add(jl1);
                jFrame.add(jt1);
                jFrame.add(jl2);
                jFrame.add(jt2);
                jFrame.add(jl3);
                jFrame.add(jt3);
                jFrame.add(jl4);
                jFrame.add(jt4);
                jFrame.add(tjb);

                jFrame.setVisible(true);
                tjb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ReaderInfo readerInfo = new ReaderInfo();
                        //String readerId,String Name, String password, String state
                        readerInfo.updateReader(jt1.getText(),jt2.getText(),jt3.getText(),jt4.getText());
                        refreshData();
                        jFrame.setVisible(false);
                    }
                });
            }
        });

        updateStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String jobId,String personId,String Name,String password,String phoneNum
                final JFrame jFrame = new JFrame("更新员工信息");
                jFrame.setBounds(500,500,400,420);
                jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 10));
                //jFrame.setLayout(new GridLayout(10,1));

                Font ft = new Font("宋体",Font.BOLD,15);
                JLabel jl1 = new JLabel("jobId:");
                JLabel jl2 = new JLabel("personId:");
                JLabel jl3 = new JLabel("Name:");
                JLabel jl4 = new JLabel("password:");
                JLabel jl5 = new JLabel("phoneNum:");
                jl1.setFont(ft);
                jl2.setFont(ft);
                jl3.setFont(ft);
                jl4.setFont(ft);
                jl5.setFont(ft);
                final JTextField jt1 = new JTextField(15);
                final JTextField jt2 = new JTextField(15);
                final JTextField jt3 = new JTextField(15);
                final JTextField jt4 = new JTextField(15);
                final JTextField jt5 = new JTextField(15);
                JButton tjb = new JButton("确认");
                //String readerId,String Name, String password, String state
                jFrame.add(jl1);
                jFrame.add(jt1);
                jFrame.add(jl2);
                jFrame.add(jt2);
                jFrame.add(jl3);
                jFrame.add(jt3);
                jFrame.add(jl4);
                jFrame.add(jt4);
                jFrame.add(jl5);
                jFrame.add(jt5);
                jFrame.add(tjb);

                jFrame.setVisible(true);
                tjb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        StaffInfo staffInfo = new StaffInfo();
                        staffInfo.updateStaff(jt1.getText(),jt2.getText(),jt3.getText(),jt4.getText(),jt5.getText());
                        refreshData();
                        jFrame.setVisible(false);
                    }
                });
            }
        });

    }
    public int importReaderData(){
        ReaderInfo readerInfo = new ReaderInfo();
        readerInfo.getReader();
        int size = readerInfo.getReaderId().size();
        Vector<String>readerId = readerInfo.getReaderId();
        Vector<String>Name = readerInfo.getName();
        Vector<String>password = readerInfo.getPassword();
        Vector<String>state = readerInfo.getState();
        reader = new String[size][4];
        for (int i = 0; i < size; i++) {
            reader[i][0] = readerId.get(i);
            reader[i][1] = Name.get(i);
            reader[i][2] = password.get(i);
            reader[i][3] = state.get(i);
        }
        return size;
    }
    public int importStaffData(){
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.getStaff();
        int size = staffInfo.getJobId().size();
        Vector<String>jobId = staffInfo.getJobId();
        Vector<String>personId = staffInfo.getPersonId();
        Vector<String>Name = staffInfo.getName();
        Vector<String>phoneNum = staffInfo.getPhoneNum();
        staff = new String[size][4];
        for (int i = 0; i < size; i++) {
            staff[i][0] = jobId.get(i);
            staff[i][1] = personId.get(i);
            staff[i][2] = Name.get(i);
            staff[i][3] = phoneNum.get(i);
        }

        return size;
    }
    public void refreshData(){
        importReaderData();
        importStaffData();
        clientFrame.remove(staffPanel);
        clientFrame.remove(readerPanel);
        createStaffPanel();
        createReaderPanel();
        readerPanel.updateUI();
        staffPanel.updateUI();
        clientFrame.add(readerPanel,BorderLayout.WEST);
        clientFrame.add(staffPanel,BorderLayout.EAST);
        clientFrame.setVisible(false);
        clientFrame.setVisible(true);
    }

    public static void main(String[] args) {
        ClientUI ui = new ClientUI();
    }
}
