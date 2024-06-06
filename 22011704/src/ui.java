import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

//import db1;

public class ui {
	//Login UI
		class LoginUI extends JFrame{
			JButton AdminButton,UserButton;

			LoginUI(){
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(300, 200);
				setTitle("Login Page");
				JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
				
				Container c = getContentPane();
		        c.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50)); // ���η� ����

		        AdminButton = new JButton("������ �α���");
		        UserButton = new JButton("����� �α���");
		        // AdminButton�� ActionListener
		        AdminButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	//������ UI ���� 
		            }
		        });

		        // UserButton�� ActionListener
		        UserButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	//����� UI ���� 
		            }
		        });

				panel.add(AdminButton);
				panel.add(UserButton);
		        getContentPane().add(panel);

				setVisible(true);

			}
		}
		//Admin UI
		//�޺� �ڽ� �����ϸ� ȭ�� ���� �޶����°� �����ؾߵ� 
		class AdminUI extends JFrame  implements ActionListener {
		    private JTextField conditionTextField, conditionTableField;
		    private JButton deleteButton, updateButton, resetButton, retrieveButton, insertButton;
		    private JTable dataTable;
		    private JComboBox<String> tableComboBox;
		    private JTextField[] inputFields;
		    private JLabel[] inputLabels;
		    private JPanel inputPanel;
		    private GridBagConstraints gbc = new GridBagConstraints();

		    private String[][] tableAttributes = { //&���� ���� 
		            {"��ȭ��", "��ȭ���", "����", "���", "�帣", "��ȭ �ٰŸ�", "������", "����"},
		            {"�¼� ���� ��", "�¼� ���� ��", "��� ��"},
		            {"��ȭ ��ȣ", "�󿵰� ��ȣ", "����", "����", "ȸ��", "���� �ð�"},
		            {"�����ID", "����� �̸�", "��ȭ��ȣ", "�̸���"},
		            {"���� ���","��������","�� �ݾ�", "����� ID", "���� ��¥"},
		            {"�󿵰� ��ȣ", "�¼���뿩��", "�¼� x��ǥ", "�¼� y��ǥ", "��������ȣ"},
		            {"��������ȣ", "�󿵰� ��ȣ", "�¼� ��ȣ", "���� ��ȣ", "�߱� ����", "ǥ�ذ���", "�ǸŰ���"}
		    };

		    AdminUI() {
		        setTitle("Admin Interface");
		        setSize(800, 500);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        setLayout(new GridBagLayout());

		        JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        JLabel conditionLabel = new JLabel("���ǽ�:");
		        conditionTextField = new JTextField(15);
		        JLabel conditionTableLabel = new JLabel("���̺�:");
		        conditionTableField = new JTextField(15);

		        conditionPanel.add(conditionLabel);
		        conditionPanel.add(conditionTextField);
		        conditionPanel.add(conditionTableLabel);
		        conditionPanel.add(conditionTableField);

		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        gbc.gridwidth = 2;
		        gbc.gridheight = 1;
		        gbc.weightx = 1.0;
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.anchor = GridBagConstraints.CENTER;
		        gbc.insets = new Insets(10, 10, 10, 10);
		        add(conditionPanel, gbc);

		        //dataTable = new JTable();
		        DefaultListModel<String> listModel = new DefaultListModel<>();
		        
		        // JList ���� �� ������ �� ����
		        JList<String> list = new JList<>(listModel);
		        
		        
		        JScrollPane scrollPane = new JScrollPane(list);
		        scrollPane.setPreferredSize(new Dimension(600, 300));

		        gbc.gridx = 0;
		        gbc.gridy = 1;
		        gbc.gridwidth = 2;
		        gbc.gridheight = 1;
		        gbc.weightx = 1.0;
		        gbc.weighty = 1.0;
		        gbc.fill = GridBagConstraints.BOTH;
		        gbc.insets = new Insets(0, 10, 10, 10);
		        add(scrollPane, gbc);

		        JPanel buttonPanel = new JPanel();
		        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

		        deleteButton = new JButton("����");
		        updateButton = new JButton("����");
		        resetButton = new JButton("�ʱ�ȭ");
		        retrieveButton = new JButton("��ü ���̺� ��ȸ");
		        
		        retrieveButton.addActionListener((new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	List<String> result = db1.printAllTable();
		            	listModel.clear();
		            	for (String item : result) {
		                    if (!listModel.contains(item)) {
		                        listModel.addElement(item);
		                    }
		                }
		            }
		        })); 
		        resetButton.addActionListener((new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	db1.init();
		            }
		        })); 
		        
		        deleteButton.addActionListener((new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	String Cond = conditionTextField.getText();
		            	String Table = conditionTableField.getText();
		            	if (Cond.length() > 0 && Table.length() >0) {
		            		if(db1.delete(Table,Cond) <0 ) {
		            			JOptionPane.showMessageDialog(null, "���ǽ� Ȥ�� ���̺� �߸��� ���� �� �ֽ��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		            		}
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "���ǽİ� ���̺��� ��ĭ���� �� �� �����ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		            	}
		            }
		        })); 
		        
		        updateButton.addActionListener((new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	String Cond = conditionTextField.getText();
		            	String[] parts = Cond.split("\\$", 2);
		            	String Table = conditionTableField.getText();
		            	if (Cond.length() > 0 && Table.length() >0 && parts.length == 2) {
		            		if(db1.update(Table,parts[0], parts[1]) <0 ) {
		            			JOptionPane.showMessageDialog(null, "���ǽ� Ȥ�� ���̺� �߸��� ���� �� �ֽ��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		            		}
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "���ǽİ� ���̺��� ��ĭ���� �� �� ������ ���ǽ��� $�� ���� set���ǰ� where ������ ������ �ۼ��ؾ� �մϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		            	}
		            }
		        })); 
		    	
		        Dimension buttonSize = new Dimension(120, 30);
		        deleteButton.setPreferredSize(buttonSize);
		        updateButton.setPreferredSize(buttonSize);
		        resetButton.setPreferredSize(buttonSize);
		        retrieveButton.setPreferredSize(buttonSize);

		        buttonPanel.add(deleteButton);
		        buttonPanel.add(updateButton);
		        buttonPanel.add(resetButton);
		        buttonPanel.add(retrieveButton);

		        gbc.gridx = 0;
		        gbc.gridy = 2;
		        gbc.gridwidth = 2;
		        gbc.gridheight = 1;
		        gbc.weightx = 1.0;
		        gbc.weighty = 0.0;
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.anchor = GridBagConstraints.NORTH;
		        gbc.insets = new Insets(10, 10, 10, 10);
		        add(buttonPanel, gbc);

		        tableComboBox = new JComboBox<>(new String[]{"��ȭ", "�󿵰�", "�� ����", "ȸ��", "����", "�¼�", "Ƽ��"});
		        tableComboBox.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                updateInputFields();
		            }
		        });

		        gbc.gridx = 2;
		        gbc.gridy = 0;
		        gbc.gridwidth = 1;
		        gbc.gridheight = 1;
		        gbc.weightx = 0.5;
		        gbc.weighty = 0.0;
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.anchor = GridBagConstraints.NORTHWEST;
		        gbc.insets = new Insets(10, 0, 10, 10);
		        add(tableComboBox, gbc);

		        inputPanel = new JPanel();
		        inputPanel.setLayout(new GridBagLayout());


		        insertButton = new JButton("�Է�");
		        insertButton.setPreferredSize(buttonSize);

		        gbc.gridx = 2;
		        gbc.gridy = 2;
		        gbc.gridwidth = 1;
		        gbc.gridheight = 1;
		        gbc.weightx = 0.0; 
		        gbc.weighty = 0.0;
		        gbc.fill = GridBagConstraints.BOTH;
		        gbc.anchor = GridBagConstraints.CENTER;
		        gbc.insets = new Insets(10, 10, 10, 10);
		        add(insertButton, gbc);

		        insertButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                String[] values = new String[inputFields.length];
		                for (int i = 0; i < inputFields.length; i++) {
		                    values[i] = inputFields[i].getText().strip();
		                    inputFields[i].setText("");
		                    if (values[i].length() == 0) {
		                    	JOptionPane.showMessageDialog(null, "�Է� �� ��ĭ�� ����� �մϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		                    	return;
		                    }
		                }
		                String tabName = tableComboBox.getSelectedItem().toString();
		                String colString = "(";
		                String inpTab = "";
		                switch (tabName) {
			                case "��ȭ":
			                	colString += "\'"+values[0]+"\', ";
			                	colString += values[1]+", ";
			                	colString += "\'"+values[2]+"\', ";
			                	colString += "\'"+values[3]+"\', ";
			                	colString += "\'"+values[4]+"\', ";
			                	colString += "\'"+values[5]+"\', ";
			                	colString += "\'"+values[6]+"\', ";
			                	colString += values[7]+")";
			                	inpTab = "movie";
			                	break;
			                case "�󿵰�":
			                	colString += values[0]+", ";
			                	colString += values[1]+", ";
			                	colString += values[2]+")";
			                	inpTab = "room";
			                	break;
			                case "�� ����":
			                	colString += values[0]+", ";
			                	colString += values[1]+", ";
			                	colString += "\'"+values[2]+"\', ";
			                	colString += "\'"+values[3]+"\', ";
			                	colString += values[4]+", ";
			                	colString += "\'"+values[5]+"\')";
			                	inpTab = "showSchedule";
			                	break;
			                case "ȸ��":
			                	colString += "\'"+values[0]+"\', ";
			                	colString += "\'"+values[1]+"\', ";
			                	colString += "\'"+values[2]+"\', ";
			                	colString += "\'"+values[3]+"\')";
			                	inpTab = "userInfo";
			                	break;
			                case "����":
			                	colString += "\'"+values[0]+"\', ";
			                	colString += values[1]+", ";
			                	colString += values[2]+", ";
			                	colString += "\'"+values[3]+"\', ";
			                	colString += "\'"+values[4]+"\')";
			                	inpTab = "payInfo";
			                	break;
			                case "�¼�":
			                	colString += values[0]+", ";
			                	colString += values[1]+", ";
			                	colString += values[2]+", ";
			                	colString += values[3]+", ";
			                	colString += values[4]+")";
			                	inpTab = "seat";
			                	try{
			                        int number = Integer.parseInt(values[4]);
			                        List<String> returnResult = db1.seatBySchedule(number);
			                        int c = Integer.parseInt(values[2]);
			                        int r = Integer.parseInt(values[3]);
			                        String limit = returnResult.removeFirst();
			                        String [] limitParts = limit.split(",", 2);
			                        int rc = Integer.parseInt(limitParts[0]);
			                        int cc = Integer.parseInt(limitParts[1]);
			                        if(c>=cc || r >= rc || c < 0 || r<0) {
			                        	JOptionPane.showMessageDialog(null, "������ �¼� ���� ����� �¼� ��ǥ�� �Է��߽��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
			                        	return;
			                        }
			                    }
			                    catch (NumberFormatException ex){
			                    	JOptionPane.showMessageDialog(null, "�Է� ���� �߸��� ���� �ֽ��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
			                        return;
			                    }
			                	break;
			                case "Ƽ��":
			                	colString += values[0]+", ";
			                	colString += values[1]+", ";
			                	colString += values[2]+", ";
			                	colString += values[3]+", ";
			                	colString += values[4]+", ";
			                	colString += values[5]+", ";
			                	colString += values[6]+")";
			                	inpTab = "ticket";
			                	break;
		                	default :
		                		break;
		                }
		                if(db1.addData(inpTab, colString)<0) {
		                	JOptionPane.showMessageDialog(null, "�Է� ���� �߸��� ���� �ֽ��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        });

		        setVisible(true);
		    }

		    void createInputFields(String[] attributes) {
		        if (inputPanel == null) {
		            inputPanel = new JPanel();
		            inputPanel.setLayout(new GridBagLayout());
		            // ��Ÿ �ʱ�ȭ �۾�
		            GridBagConstraints inputGbc = new GridBagConstraints();
		            inputGbc.anchor = GridBagConstraints.WEST;
		            inputGbc.insets = new Insets(5, 10, 5, 10);

		        } else {
		            inputPanel.removeAll();
		        }

		        GridBagConstraints inputGbc = new GridBagConstraints();
		        inputGbc.anchor = GridBagConstraints.WEST;
		        inputGbc.insets = new Insets(5, 10, 5, 10);

		        inputFields = new JTextField[attributes.length];
		        inputLabels = new JLabel[attributes.length];

		        // �Է� �ʵ�� �� �߰�
		        for (int i = 0; i < attributes.length; i++) {
		            inputLabels[i] = new JLabel(attributes[i] + ":");
		            inputFields[i] = new JTextField(20);

		            inputGbc.gridx = 0;
		            inputGbc.gridy = i;
		            inputGbc.weightx = 0.0;
		            inputGbc.fill = GridBagConstraints.NONE;
		            inputGbc.gridwidth = 1;
		            inputPanel.add(inputLabels[i], inputGbc);

		            inputGbc.gridx = 1;
		            inputGbc.weightx = 1.0;
		            inputGbc.fill = GridBagConstraints.HORIZONTAL;
		            inputPanel.add(inputFields[i], inputGbc);
		        }
		        gbc.gridx = 2;
		        gbc.gridy = 1; 
		        gbc.gridwidth = 1;
		        gbc.gridheight = 1;
		        gbc.weightx = 0.0;
		        gbc.weighty = 0.0;
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.anchor = GridBagConstraints.NORTH;
		        gbc.insets = new Insets(10, 10, 10, 10);
		        add(inputPanel, gbc);

		        revalidate(); // �������� �ٽ� ��ȿȭ�Ͽ� ���� ������ ����
		        repaint(); // ȭ���� �ٽ� �׷��� ���� ������ ǥ��
		        
		    }

		    private void updateInputFields() {
		        int selectedIndex = tableComboBox.getSelectedIndex();
		        if (selectedIndex >= 0 && selectedIndex < tableAttributes.length) {
		            createInputFields(tableAttributes[selectedIndex]);
		        }
		    }

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}

		}

		// usermain UI
		class UsermainUI extends JFrame {

			public UsermainUI() {
		        setTitle("User main UI");
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setSize(700, 500);
		        setLocationRelativeTo(null); // ȭ�� �߾ӿ� ��ġ

		        // ��ü UI �г�
		        JPanel mainPanel = new JPanel(new BorderLayout());
		        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // ��ü �������� ���� �߰�

		        // ��� �г� (������, ���� ���� Ȯ�� ��ư)
		        JPanel topPanel = new JPanel(new BorderLayout());
		        JPanel topdatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        JLabel dateLabel = new JLabel("������: ");
		        JTextField dateTextField = new JTextField(10); // ���� ��¥�� �Է¹޴� �ʵ�
		        topdatePanel.add(dateLabel);
		        topdatePanel.add(dateTextField); // ������ �г�

		        JButton confirmBookingButton = new JButton("���� ���� Ȯ��");
		        topPanel.add(topdatePanel, BorderLayout.WEST);
		        topPanel.add(confirmBookingButton, BorderLayout.EAST);
		        mainPanel.add(topPanel, BorderLayout.NORTH);

		        // ���� �г� (�˻� ����)
		        JPanel leftPanel = new JPanel(new BorderLayout());
		        JPanel searchPanel = new JPanel();
		        searchPanel.setLayout(new GridBagLayout());
		        GridBagConstraints gbc = new GridBagConstraints();
		        gbc.insets = new Insets(5, 5, 5, 5); // ���� ����

		        // ��ȭ�� �г�
		        JLabel movieLabel = new JLabel("��ȭ��: ");
		        JTextField movieField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        searchPanel.add(movieLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(movieField, gbc);

		        // ������ �г�
		        JLabel directorLabel = new JLabel("������: ");
		        JTextField directorField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy++;
		        searchPanel.add(directorLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(directorField, gbc);

		        // ��� �г�
		        JLabel actorLabel = new JLabel("���: ");
		        JTextField actorField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy++;
		        searchPanel.add(actorLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(actorField, gbc);

		        // �帣 �г�
		        JLabel genreLabel = new JLabel("�帣: ");
		        JTextField genreField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy++;
		        searchPanel.add(genreLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(genreField, gbc);

		        leftPanel.add(searchPanel, BorderLayout.CENTER);

		        // �˻� ��ư �г�
		        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        JButton searchButton = new JButton("���� ��ȸ");
		        JButton searchAllButton = new JButton("��ü ��ȸ");
		        buttonPanel.add(searchButton);
		        buttonPanel.add(searchAllButton);
		        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

		        mainPanel.add(leftPanel, BorderLayout.WEST);

		        // ������ �г� (������ ���̺�)
		        JPanel rightPanel = new JPanel(new BorderLayout());
		        String[] columnNames = {"��ȭ��", "������", "���", "�帣"};
		        Object[][] data = {
		                {"��ȭ1", "����1", "���1", "�帣1"},
		                {"��ȭ2", "����2", "���2", "�帣2"},
		                // �����ʹ� ����
		        };
		        DefaultTableModel model = new DefaultTableModel(data, columnNames);
		        JTable table = new JTable(model);
		        table.setPreferredScrollableViewportSize(new Dimension(400, 400)); // ���̺� ũ�� ����
		        table.setFillsViewportHeight(true); // ���̺��� ��ü ���̸� ä�쵵�� ����
		        JScrollPane scrollPane = new JScrollPane(table);
		        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // ���� �߰�
		        rightPanel.add(scrollPane, BorderLayout.CENTER);
		        mainPanel.add(rightPanel, BorderLayout.CENTER);

		        add(mainPanel);

		        // ���̺� Ŭ�� �̺�Ʈ ó��
		        table.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		                int row = table.getSelectedRow();
		                if (row != -1) {
		                    String movieName = (String) table.getValueAt(row, 0);
		                    String directorName = (String) table.getValueAt(row, 1);
		                    String actorName = (String) table.getValueAt(row, 2);
		                    String genre = (String) table.getValueAt(row, 3);
		                    openSeatBookingUI(movieName, directorName, actorName, genre);
		                }
		            }
		        });

		        
		        setVisible(true);
		    }
		    private void openSeatBookingUI(String movieName, String directorName, String actorName, String genre) {
		        // �¼� ���� UI�� ���ο� â���� ���ϴ�.
		    	List<String> reservedSeats = Arrays.asList("1-1", "2-3", "5-7");
		        new SeatBookingUI(movieName, directorName, actorName, genre,reservedSeats ).setVisible(true);
		    }
		}

		class SeatButton extends JButton {
		    private boolean isSelected = false;
		    private String seatNumber;
		    private boolean isReserved;

		    public SeatButton(String seatNumber, ActionListener listener) {
		        super(seatNumber);
		        this.seatNumber = seatNumber;
		        this.isReserved = false;
		        this.addActionListener(listener);
		        this.setPreferredSize(new Dimension(50, 50)); // ���簢�� ��ư ũ�� ����
		    }
		    public String getSeatNumber() {
		        return seatNumber;
		    }
		    public void select() {
		        if (!isReserved) {
		            isSelected = true;
		            setBackground(Color.PINK); // ���õ� �¼��� ���� ����
		        }
		    }

		    public void deselect() {
		        if (!isReserved) {
		            isSelected = false;
		            setBackground(null); // �⺻������ �ǵ���
		        }
		    }
		    public void reserve() {
		        isReserved = true;
		        setEnabled(false);
		        setBackground(Color.darkGray); // ����� �¼��� ���� ����
		    }

		    public void unreserve() {
		        isReserved = false;
		        setEnabled(true);
		        setBackground(null); // �⺻������ �ǵ���
		    }

		    public boolean isReserved() {
		        return isReserved;
		    }
		    public boolean isSelected() {
		        return isSelected;
		    }
		    public void toggleSelection() {
		        if (!isReserved) {
		            if (isSelected) {
		                deselect();
		            } else {
		                select();
		            }
		        }
		    }
		}

		 class SeatBookingUI extends JFrame {
		    private JLabel selectedSeatLabel;
		    private final int rows = 10;
		    private final int cols = 10;
		    private List<SeatButton> selectedSeats = new ArrayList<>();

		    public SeatBookingUI(String movieName, String directorName, String actorName, String genre, List<String> reservedSeats) {
		        setTitle("Seat Select Page - " + movieName);
		        setSize(700, 500);
		        setLocationRelativeTo(null);
		        setLayout(new BorderLayout());
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        JPanel seatPanel = new JPanel(new GridLayout(rows, cols));
		        ActionListener seatButtonListener = new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                SeatButton seatButton = (SeatButton) e.getSource();
		                seatButton.toggleSelection();
		                updateSelectedSeats();
		            }
		        };
		        for (int i = 0; i < rows; i++) {
		            for (int j = 0; j < cols; j++) {
		                String seatNumber = (i + 1) + "-" + (j + 1);
		                SeatButton seat = new SeatButton(seatNumber, seatButtonListener);
		                if (reservedSeats.contains(seatNumber)) {
		                    seat.reserve();
		                }
		                seatPanel.add(seat);
		            }
		        }

		        JPanel seatPanelWrapper = new JPanel(new BorderLayout());
		        seatPanelWrapper.setBorder(new EmptyBorder(20, 20, 20, 20));
		        seatPanelWrapper.add(seatPanel, BorderLayout.CENTER);

		        JPanel bottomPanel = new JPanel(new BorderLayout());
		        selectedSeatLabel = new JLabel("������ �¼�: ����");
		        selectedSeatLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
		        JButton bookButton = new JButton("�����ϱ�");
		        bookButton.setPreferredSize(new Dimension(150, 50));
		        bookButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                bookSeats();
		            }
		        });

		        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		        buttonPanel.add(bookButton);

		        bottomPanel.add(selectedSeatLabel, BorderLayout.CENTER);
		        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

		        add(seatPanelWrapper, BorderLayout.CENTER);
		        add(bottomPanel, BorderLayout.SOUTH);
		    }

		    private void updateSelectedSeats() {
		        selectedSeats.clear();
		        StringBuilder seatsText = new StringBuilder("������ �¼�: ");
		        JPanel seatPanelWrapper = (JPanel) getContentPane().getComponent(0); // seatPanelWrapper ��������
		        JPanel seatPanel = (JPanel) seatPanelWrapper.getComponent(0); // seatPanel ��������

		        for (Component comp : seatPanel.getComponents()) {
		            if (comp instanceof SeatButton) {
		                SeatButton seatButton = (SeatButton) comp;
		                if (seatButton.isSelected()) {
		                    selectedSeats.add(seatButton);
		                    seatsText.append(seatButton.getSeatNumber()).append(" ");
		                }
		            }
		        }

		        if (selectedSeats.isEmpty()) {
		            selectedSeatLabel.setText("������ �¼�: ����");
		        } else {
		            selectedSeatLabel.setText(seatsText.toString());
		        }
		    }

		    private void bookSeats() {
		        if (selectedSeats.isEmpty()) {
		            JOptionPane.showMessageDialog(this, "���õ� �¼��� �����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
		        } else {
		            for (SeatButton seatButton : selectedSeats) {
		                seatButton.reserve();
		            }
		            selectedSeats.clear();
		            updateSelectedSeats();
		            JOptionPane.showMessageDialog(this, "�¼��� ���ŵǾ����ϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		}

		class ReservationUI extends JFrame {
		    private JTable table;
		    private DefaultTableModel model;

		    public ReservationUI() {
		        // ������ ����
		        setTitle("���� ����");
		        setSize(800, 500);
		        setLocationRelativeTo(null);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        // ���̺� �� ����
		        model = new DefaultTableModel();
		        model.addColumn("��ȭ��");
		        model.addColumn("����");
		        model.addColumn("�󿵰���ȣ");
		        model.addColumn("�¼���ȣ");
		        model.addColumn("�Ǹ� ����");

		        // ���̺� ���� �� �� �Ҵ�
		        table = new JTable(model);

		        // ��ũ�� �гο� ���̺� �߰�
		        JScrollPane scrollPane = new JScrollPane(table);

		        // �г� ���� �� �׵θ� ����
		        JPanel panel = new JPanel(new BorderLayout());
		        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        panel.add(scrollPane, BorderLayout.CENTER);

		        // �����ӿ� �г� �߰�
		        add(panel, BorderLayout.CENTER);
		        setVisible(true);
		        table.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		                if (e.getClickCount() == 1) {
		                    int selectedRow = table.getSelectedRow();
		                    if (selectedRow != -1) {
		                        //String movieName = (String) tableModel.getValueAt(selectedRow, 0);
		                        //showDetailedInfo(movieName);
		                    }
		                }
		            }
		        });
		    }

		}
		class DetailedreservationUI extends JFrame {
			 private DefaultTableModel tableModel;
			    private JTable table;
			    private JTextField searchField;
			    private Vector<Object[]> detailedData; // ���� ������ �����ϴ� ����

			    public DetailedreservationUI() {
			        setTitle("���� �ý���");
			        setSize(800, 500);
			        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        setLocationRelativeTo(null);
			        setLayout(new BorderLayout());

			        detailedData = new Vector<>();
			        initializeUI();
			    }

			    private void initializeUI() {
			        // ���� �г� (���̺�)
			        JPanel leftPanel = new JPanel(new BorderLayout());
			        String[] columnNames = {"���� ��ȣ", "��ȭ", "������", "�󿵰�", "Ƽ�� ����"};
			        tableModel = new DefaultTableModel(columnNames, 0);
			        table = new JTable(tableModel);
			        JScrollPane scrollPane = new JScrollPane(table);
			        leftPanel.add(scrollPane, BorderLayout.CENTER);

			        // ���� ���� �߰� ����
			        addReservationData(new Object[]{"1", "Movie 1", "2024-06-01 14:00", "Theater 1", "A1"});
			        addReservationData(new Object[]{"2", "Movie 2", "2024-06-02 16:00", "Theater 2", "B2"});

			        // ������ �г� (�˻� �ʵ�� ��ư��)
			        JPanel rightPanel = new JPanel();
			        rightPanel.setLayout(new GridBagLayout());
			        GridBagConstraints gbc = new GridBagConstraints();
			        gbc.insets = new Insets(5, 5, 5, 5);
			        
			        searchField = new JTextField(10);	     
			        JButton searchButton = new JButton("��ȸ");
			        JButton changeMovieButton = new JButton("��ȭ ����");
			        JButton changeScheduleButton = new JButton("���� ����");
			        JButton deleteReservationButton = new JButton("���� ����");

			        searchButton.addActionListener(this::searchAction);
			        changeMovieButton.addActionListener(this::changeMovieAction);
			        changeScheduleButton.addActionListener(this::changeScheduleAction);
			        deleteReservationButton.addActionListener(this::deleteReservationAction);
			     // �˻� �ʵ�� ��ȸ ��ư�� �� �ٿ� ��ġ
			        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			        searchPanel.add(searchField);
			        searchPanel.add(searchButton);
			        
			        gbc.gridx = 0;
			        gbc.gridy = 0;
			        gbc.gridwidth = 2;
			        rightPanel.add(searchPanel, gbc);

			        gbc.gridx = 0;
			        gbc.gridy = 1;
			        rightPanel.add(changeMovieButton, gbc);

			        gbc.gridx = 0;
			        gbc.gridy = 2;
			        rightPanel.add(changeScheduleButton, gbc);

			        gbc.gridx = 0;
			        gbc.gridy = 3;
			        rightPanel.add(deleteReservationButton, gbc);

			        // ���� �߰�
			        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			        add(leftPanel, BorderLayout.CENTER);
			        add(rightPanel, BorderLayout.EAST);
			        setVisible(true);
			    }

			    private void addReservationData(Object[] data) {
			        detailedData.add(data);
			        tableModel.addRow(data);
			    }

			    private void searchAction(ActionEvent e) {
			        String searchText = searchField.getText().trim();
			        for (Object[] row : detailedData) {
			            if (row[0].equals(searchText)) {
			                JOptionPane.showMessageDialog(this, "���� ��ȣ: " + row[0] + "\n��ȭ: " + row[1] + "\n������: " + row[2] + "\n�󿵰�: " + row[3] + "\nƼ�� ����: " + row[4], "���� ����", JOptionPane.INFORMATION_MESSAGE);
			                return;
			            }
			        }
			        JOptionPane.showMessageDialog(this, "�ش� ���� ��ȣ�� ã�� �� �����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			    }

			    private void changeMovieAction(ActionEvent e) {
			    	//���� ���� 
			        int selectedRow = table.getSelectedRow();
			        if (selectedRow == -1) {
			            JOptionPane.showMessageDialog(this, "������ ���Ÿ� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        String movieName = JOptionPane.showInputDialog(this, "�� ��ȭ���� �Է��ϼ���:");
			        if (movieName != null && !movieName.trim().isEmpty()) {
			            tableModel.setValueAt(movieName, selectedRow, 1);
			            detailedData.get(selectedRow)[1] = movieName;
			            JOptionPane.showMessageDialog(this, "��ȭ�� ����Ǿ����ϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }

			    private void changeScheduleAction(ActionEvent e) {
			        int selectedRow = table.getSelectedRow();
			        if (selectedRow == -1) {
			            JOptionPane.showMessageDialog(this, "������ ���Ÿ� �������ּ���.", "����", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        String schedule = JOptionPane.showInputDialog(this, "�� �� ������ �Է��ϼ��� ");
			        if (schedule != null && !schedule.trim().isEmpty()) {
			            tableModel.setValueAt(schedule, selectedRow, 2);
			            detailedData.get(selectedRow)[2] = schedule;
			            JOptionPane.showMessageDialog(this, "�� ������ ����Ǿ����ϴ�.", "���� �Ϸ�", JOptionPane.INFORMATION_MESSAGE);
			        }
			    }

			    private void deleteReservationAction(ActionEvent e) {
			        String searchText = searchField.getText().trim();
			        for (int i = 0; i < detailedData.size(); i++) {
			            if (detailedData.get(i)[0].equals(searchText)) {
			                detailedData.remove(i);
			                tableModel.removeRow(i);
			                JOptionPane.showMessageDialog(this, "���Ű� �����Ǿ����ϴ�.", "���� ����", JOptionPane.INFORMATION_MESSAGE);
			                return;
			            }
			        }
			        JOptionPane.showMessageDialog(this, "�ش� ���� ��ȣ�� ã�� �� �����ϴ�.", "����", JOptionPane.ERROR_MESSAGE);
			    }
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { new
		 * AdminUI(); } });
		 */
		ui uiInstance = new ui();
		AdminUI aInstance = uiInstance.new AdminUI();
		//new LoginUI();
		//new UsermainUI();
		//new ReservationUI();
		//new DetailedreservationUI();
	}

}
