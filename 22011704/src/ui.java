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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.DefaultTableModel;

//import db1;

public class ui {
	//Login UI
	class LoginUI extends JFrame implements ActionListener{
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
	            	 AdminUI adminUI = new AdminUI();
	                 adminUI.setVisible(true);
	            }
	        });

	        // UserButton�� ActionListener
	        UserButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//����� UI ���� 
	            	 UsermainUI userUI = new UsermainUI();
	                 userUI.setVisible(true);
	            	
	            }
	        });

			panel.add(AdminButton);
			panel.add(UserButton);
	        getContentPane().add(panel);

			setVisible(true);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
		//Admin UI
		//�޺� �ڽ� �����ϸ� ȭ�� ���� �޶����°� �����ؾߵ� 
		class AdminUI extends JFrame  implements ActionListener {
		    private JTextField conditionTextField, conditionTableField;
		    private JButton deleteButton, updateButton, resetButton, retrieveButton, insertButton;
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
			                        int rc = Integer.parseInt(limitParts[0].strip());
			                        int cc = Integer.parseInt(limitParts[1].strip());
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
		class UsermainUI extends JFrame  implements ActionListener{
			private DefaultListModel<String> listModel;
		    private JList<String> list;
			 UsermainUI() {
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

		        JButton confirmBookingButton = new JButton("���� ���� Ȯ��");
		        topPanel.add(topdatePanel, BorderLayout.WEST);
		        topPanel.add(confirmBookingButton, BorderLayout.EAST);
		        mainPanel.add(topPanel, BorderLayout.NORTH);
		        
		        confirmBookingButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	List<String> result = db1.checkMyPayment();
		            	if(result.size()==0) {
		            		JOptionPane.showMessageDialog(null, "���� ������ �����ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		            	}
		            	else {
		            	 ReservationUI reservationUI = new ReservationUI();
		                 reservationUI.setVisible(true);
		            	}
		            }
		        });
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
		        listModel = new DefaultListModel<>();
		        
		        // JList ���� �� ������ �� ����
		        list = new JList<>(listModel);
		        JScrollPane scrollPane = new JScrollPane(list);
		        JPanel rightPanel = new JPanel(new BorderLayout());
		        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		        rightPanel.add(scrollPane, BorderLayout.CENTER);
		        mainPanel.add(rightPanel, BorderLayout.CENTER);

		        getContentPane().add(mainPanel);
		        setVisible(true);
		        List<String> initialData = new ArrayList<>();
		        initialData.addAll(db1.printMovie("", "","",""));
		        
		        //���� ��ȸ 
		        searchButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                String movie = movieField.getText();
		                String director = directorField.getText();
		                String actor = actorField.getText();
		                String genre = genreField.getText();
		                List<String> result = db1.printMovie(movie, director, actor, genre);
		                updateList(result);
		            }
		        });
		        searchAllButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                List<String> result = db1.printMovie("", "", "", "");
		                updateList(result);
		            }
		        });
		        list.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                if (e.getClickCount() == 2) { // Ŭ���� 2�� ���� ��
		                    int index = list.locationToIndex(e.getPoint());                	

		                    if (index >= 0) {
		                        String selectedItem = listModel.getElementAt(index);

		                        String[] parts = selectedItem.split("\\|"); //|�� ���еǾ����Ƿ� 
		                        int result = db1.scheduleIdFromMovieId(Integer.parseInt(parts[1].trim()));
		                        if (result < 0) {
		                        	JOptionPane.showMessageDialog(null, "�ش� ��ȭ�� �� ������ �����ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		                        	return;
		                        }
		                        String movieName = parts[2].trim();
		                        String directorName = parts[4].trim();
		                        String actorName = parts[5].trim();
		                        String genre = parts[6].trim();
		                        openSeatBookingUI(movieName, directorName, actorName, genre, result);
		                        
		                    }
		                }
		            }
		        });
		        setVisible(true);
		    }
			private void updateList(List<String> data) {
		        SwingUtilities.invokeLater(new Runnable() {
		            @Override
		            public void run() {
		                listModel.clear();
		                for (String item : data) {
		                    if (!listModel.contains(item)) {
		                        listModel.addElement(item);
		                    }
		                }
		            }
		        });
		    }
			
		    public void openSeatBookingUI(String movieName, String directorName, String actorName, String genre, int scheduleIdInput) {
		        // �¼� ���� UI�� ���ο� â���� ���ϴ�.
		    	List<String> replySeat = db1.seatBySchedule(scheduleIdInput);
		    	String reserved = replySeat.removeLast();
		    	List<String> reservedSeats = Arrays.asList(reserved.split(" "));//&���� ���� 
		        new SeatBookingUI(movieName, directorName, actorName, genre,reservedSeats ,scheduleIdInput).setVisible(true);
		    }
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
		    private int rows = 10;
		    private int cols = 10;
		    private int scheduleIdT = 0;
		    private List<SeatButton> selectedSeats = new ArrayList<>();

		    public SeatBookingUI(String movieName, String directorName, String actorName, String genre, List<String> reservedSeats, int inputScheduleID) {
		        setTitle("Seat Select Page - " + movieName);
		        setSize(700, 500);
		        setLocationRelativeTo(null);
		        setLayout(new BorderLayout());
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        scheduleIdT = inputScheduleID;
		        JPanel seatPanel = new JPanel(new GridLayout(rows, cols));
		        ActionListener seatButtonListener = new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                SeatButton seatButton = (SeatButton) e.getSource();
		                seatButton.toggleSelection();
		                updateSelectedSeats();
		            }
		        };
		        
		        List<String> replySeat = db1.seatBySchedule(inputScheduleID);
		    	String numOfSeats = replySeat.removeFirst();
		    	cols = Integer.parseInt(numOfSeats.strip().split(",")[0]);
		    	rows = Integer.parseInt(numOfSeats.strip().split(",")[1]);
		        for (int i = 0; i < cols; i++) {
		            for (int j = 0; j < rows; j++) {
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
		        JButton checkButton = new JButton("Ȯ��");
		        checkButton.setPreferredSize(new Dimension(150, 50));
		        bookButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                bookSeats();
		            }
		        });
		        checkButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                dispose();
		            }
		        });

		        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		        buttonPanel.add(bookButton);
		        buttonPanel.add(checkButton);

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
		        	int n=0;
		        	List<Integer> xL = new ArrayList<>();
		        	List<Integer> yL = new ArrayList<>();
		        	List<Integer> cL = new ArrayList<>();
		            for (SeatButton seatButton : selectedSeats) {
		                seatButton.reserve();
		                try {
		                	String [] loc = seatButton.seatNumber.split("-");
		                	int x = Integer.parseInt(loc[0].strip());
		                	int y = Integer.parseInt(loc[1].strip());
		                	n += 1;
		                	xL.add(x);
		                	yL.add(y);
		                	cL.add(10000);
		                }
		                catch (Exception e) {
		                	System.out.println("convert error");
		                }
		            }
		            Integer[] x1 = xL.toArray(new Integer[0]);

		            int[] X = new int[x1.length];
		            for (int i = 0; i < x1.length; i++) {
		                X[i] = x1[i];
		            }
		            
		            Integer[] y1 = yL.toArray(new Integer[0]);

		            int[] Y = new int[y1.length];
		            for (int i = 0; i < y1.length; i++) {
		                Y[i] = y1[i];
		            }
		            
		            Integer[] c1 = cL.toArray(new Integer[0]);

		            int[] C = new int[c1.length];
		            for (int i = 0; i < c1.length; i++) {
		                C[i] = c1[i];
		            }
		            
		            
		            int bookResult = db1.bookTicket(n, X, Y, C, scheduleIdT, "Internet");
		            selectedSeats.clear();
		            updateSelectedSeats();
		            JOptionPane.showMessageDialog(this, "�¼��� ���ŵǾ����ϴ�.", "����", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		}

		class ReservationUI extends JFrame {
		     // ����Ʈ �� ����
			 DefaultListModel<String> listModel = new DefaultListModel<>();
		     // ����Ʈ ���� �� �� �Ҵ�
		     JList<String> list = new JList<>(listModel);

		    public ReservationUI() {
		        // ������ ����
		        setTitle("���� ����");
		        setSize(800, 500);
		        setLocationRelativeTo(null);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		        // ��ũ�� �гο� ����Ʈ �߰�
		        JScrollPane scrollPane = new JScrollPane(list);

		        // �г� ���� �� �׵θ� ����
		        JPanel panel = new JPanel(new BorderLayout());
		        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        panel.add(scrollPane, BorderLayout.CENTER);

		        // �����ӿ� �г� �߰�
		        add(panel, BorderLayout.CENTER);
		        setVisible(true);
		        List<String> result = db1.checkMyPayment();
            	listModel.clear();
            	for (String item : result) {
                    if (!listModel.contains(item)) {
                        listModel.addElement(item);
                    }
                }
            	//ListModel ��ȭ ������
            	listModel.addListDataListener((ListDataListener) new ListDataListener() {
            		
            		@Override
                    public void intervalAdded(ListDataEvent e) {
                        System.out.println("Element(s) added: " + e.getIndex0() + " to " + e.getIndex1());
                        if(listModel.size()==0) {
                        	dispose();
                        }
                    }

                    @Override
                    public void intervalRemoved(ListDataEvent e) {
                        System.out.println("Element(s) removed: " + e.getIndex0() + " to " + e.getIndex1());
                        if(listModel.size()==0) {
                        	dispose();
                        }
                    }

                    @Override
                    public void contentsChanged(ListDataEvent e) {
                        System.out.println("Contents changed: " + e.getIndex0() + " to " + e.getIndex1());
                        if(listModel.size()==0) {
                        	dispose();
                        }
                    }
                });
            	
		        // ���� �׸� Ŭ�� ������
		        list.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		                if (e.getClickCount() == 1) {
		                    int selectedIndex = list.getSelectedIndex();
		                    if (selectedIndex != -1) {
		                        // ���� �������� ���õ� �׸��� ������ �������� �ڵ� 
		                        String selectedItem = listModel.getElementAt(selectedIndex);
		                        //|�� ����, �ӽ� +++
		                        String[] parts = selectedItem.split("\\|");
		                        
		                        try{
		                        	int payIdSelected = Integer.parseInt(parts[1].strip());
		                        	DetailedreservationUI detailedReservationUI = new DetailedreservationUI(payIdSelected, listModel);
		                        	detailedReservationUI.setVisible(true);
			                    }
			                    catch (NumberFormatException ex){
			                    	JOptionPane.showMessageDialog(null, "�����Ϳ� �߸��� ���� �ֽ��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
			                        return;
			                    }
		                        
		                    }
		                }
		            }
		        });
		    }

		    // ���� ������ ����Ʈ�� �߰��ϴ� �޼��� +++ �̰� ������ �������� 
		    public void addReservation(String movieName, String screeningDate, String theaterNumber, String seatNumber, String price) {
		        String reservationInfo = movieName + " - " + screeningDate + " - " + theaterNumber + " - " + seatNumber + " - " + price;
		        listModel.addElement(reservationInfo);
		    }

		    // ���� ������ �� ������ ǥ���ϴ� �޼��� (���� ������ �����Ǿ����ϴ�)
		    private void showDetailedInfo(String movieName, String screeningDate, String theaterNumber, String seatNumber, String price) {
		        // ���⿡ �� ������ ǥ���ϴ� ������ ������ �� �ֽ��ϴ�.
		        JOptionPane.showMessageDialog(this,
		                "��ȭ��: " + movieName + "\n����: " + screeningDate + "\n�󿵰���ȣ: " + theaterNumber
		                        + "\n�¼���ȣ: " + seatNumber + "\n�Ǹ� ����: " + price,
		                "���� �� ����", JOptionPane.INFORMATION_MESSAGE);
		    }

		}
		
		class DetailedreservationUI extends JFrame {

	        public JTextField moviechangeField;
	        public JTextField timechangeField;
	        private JComboBox<String> comboBox;
	     // ����Ʈ �� ����
			DefaultListModel<String> listModel = new DefaultListModel<>();
		     // ����Ʈ ���� �� �� �Ҵ�
		    JList<String> list = new JList<>(listModel);
		    int inputPayId_IN = -1;
	        private int selectedReservationIndex = -1; // �̿��ؼ� ��ȸ�Ѱ� ���� 
	        private Vector<Object[]> detailedData; // ���� ������ �����ϴ� ���� => stinglist
		    public DetailedreservationUI(int inputPayId, DefaultListModel<String> inputList) {
		    	comboBox = new JComboBox<>(db1.getMovieList());
		    	
		    	inputPayId_IN = inputPayId;
		        setTitle("���� �ý���");
		        setSize(800, 500);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        setLocationRelativeTo(null);
		        setLayout(new BorderLayout());
		        List<String> result = db1.showDetailPayment(inputPayId);
            	listModel.clear();
            	for (String item : result) {
                    if (!listModel.contains(item)) {
                        listModel.addElement(item);
                    }
                }
            	addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosed(WindowEvent e) {
		            	inputList.clear();
		            	List<String> resultT = db1.checkMyPayment();
		            	inputList.clear();
		            	for (String item : resultT) {
		                    if (!inputList.contains(item)) {
		                    	inputList.addElement(item);
		                    }
		                }
		            }
		        });
		        
		        /*JScrollPane scrollPane = new JScrollPane(list);

		        // �г� ���� �� �׵θ� ����
		        JPanel panel = new JPanel(new BorderLayout());
		        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        panel.add(scrollPane, BorderLayout.CENTER);

		        // �����ӿ� �г� �߰�
		        add(panel, BorderLayout.CENTER);
		        
            	JPanel panel2 = new JPanel(new GridLayout(3, 1, 10, 10));
            	add(panel2, BorderLayout.EAST);
            	JButton delete = new JButton("delete");
            	JButton movieEdit = new JButton("movieEdit");
            	JButton scheduleEdit = new JButton("scheduleEdit");
            	panel2.add(delete);
            	panel2.add(movieEdit);
            	panel2.add(scheduleEdit);
            	
		        setVisible(true);
		        addWindowListener(new WindowAdapter() {
		            @Override
		            public void windowClosed(WindowEvent e) {
		            	inputList.clear();
		            	List<String> resultT = db1.checkMyPayment();
		            	inputList.clear();
		            	for (String item : resultT) {
		                    if (!inputList.contains(item)) {
		                    	inputList.addElement(item);
		                    }
		                }
		            }
		        });
		        delete.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		                int result = db1.cancelPay(inputPayId);
		                if (result<0) {
		                	JOptionPane.showMessageDialog(null, "�����Ϳ� �߸��� ���� �ֽ��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		                }
		                dispose();
		            }
		        });*/
		        //detailedData = new Vector<>();
		        initializeUI();
		    }

	        private void initializeUI() {
	            // ���� �г� (����Ʈ)
	            JPanel leftPanel = new JPanel(new BorderLayout());
	            list = new JList<>(listModel);
	            JScrollPane scrollPane = new JScrollPane(list);
	            leftPanel.add(scrollPane, BorderLayout.CENTER);

	            
	            // ������ �г� (�˻� �ʵ�� ��ư��)
	            JPanel rightPanel = new JPanel();
	            rightPanel.setLayout(new GridBagLayout());
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.insets = new Insets(5, 5, 5, 5);
	              
	            JButton checkButton = new JButton("Ȯ��");
	            JButton changeMovieButton = new JButton("��ȭ ����");
	            JButton changeScheduleButton = new JButton("���� ����");
	            JButton deleteReservationButton = new JButton("���� ����");

	            checkButton.addActionListener(this::searchAction);
	            changeMovieButton.addActionListener(this::changeMovieAction);
	            changeScheduleButton.addActionListener(this::changeScheduleAction);
	            deleteReservationButton.addActionListener(this::deleteReservationAction);
	            
	            

	         // ���� ��¥ �� ��ȭ �ʵ� �߰�
	            JLabel moviechangeLabel = new JLabel("���� ��ȭ:");
	            moviechangeField = new JTextField(10);
	            JLabel timechangeLabel = new JLabel("���� ��¥:");
	            timechangeField = new JTextField(10);

	            gbc.gridx = 0;
	            gbc.gridy = 1;
	            gbc.gridwidth = 1;
	            rightPanel.add(moviechangeLabel, gbc);
	            gbc.gridx = 1;
	            gbc.gridy = 1;
	            rightPanel.add(comboBox, gbc);

	            gbc.gridx = 0;
	            gbc.gridy = 2;
	            gbc.gridwidth = 1;
	            rightPanel.add(timechangeLabel, gbc);
	            gbc.gridx = 1;
	            gbc.gridy = 2;
	            rightPanel.add(timechangeField, gbc);

	            gbc.gridx = 0;
	            gbc.gridy = 3;
	            gbc.gridwidth = 2;
	            rightPanel.add(changeMovieButton, gbc);

	            gbc.gridx = 0;
	            gbc.gridy = 4;
	            rightPanel.add(changeScheduleButton, gbc);

	            gbc.gridx = 0;
	            gbc.gridy = 5;
	            rightPanel.add(deleteReservationButton, gbc);
	            gbc.gridx = 0;
	            gbc.gridy = 6;
	            rightPanel.add(checkButton, gbc);
	              // ���� �߰�
	            rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	            leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	            add(leftPanel, BorderLayout.CENTER);
	            add(rightPanel, BorderLayout.EAST);
	            setVisible(true);
	        }
	        

	        private void searchAction(ActionEvent e) {
	            dispose();
	        }

	        private void changeMovieAction(ActionEvent e) {
                String s_movie = (String)(comboBox.getSelectedItem());
                String [] tns_m = s_movie.split(" ",2);
                int m_id = Integer.parseInt(tns_m[0].strip());
                int sche = db1.scheduleIdFromMovieId(m_id);
                if(sche<0) {
                	JOptionPane.showMessageDialog(null, "�ش� ��ȭ�� �� ������ �����ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                	int room = db1.roomIdFromscheduleId(sche);

                    List<String> seatResult = db1.seatBySchedule(sche);
                    String limit = seatResult.removeFirst();
                    String [] limitParts = limit.split(",", 2);
                    int rc = Integer.parseInt(limitParts[0].strip());
                    int rr = Integer.parseInt(limitParts[1].strip());
                    limit = seatResult.removeFirst();
                    limitParts = limit.split(" ");
                    boolean isAlreadyBooked = false;
    		        List<String> checkSeat = db1.showDetailPayment(inputPayId_IN);
    		        for (String item : checkSeat) {
                        if (item.contains("seat")) {
                            String [] loc = item.split(" : ");
                            String realL = loc[1];
                            String [] c_r = realL.split("-");
                            
                            int c = Integer.parseInt(c_r[0].strip());
                            int r = Integer.parseInt(c_r[1].strip());
                            
                            for (int i = 0; i<limitParts.length;i++) {
                            	realL = limitParts[i];
                            	c_r = realL.split("-");
                            	int lC = Integer.parseInt(c_r[0].strip());
                            	int lR = Integer.parseInt(c_r[1].strip());
                            	
                            	if(c == lC && r == lR) {
                            		isAlreadyBooked = true;
                            		break;
                            	}
                            }
                            if (c>rc || r>rr || isAlreadyBooked) {
                            	JOptionPane.showMessageDialog(null, "���� ���� �󿵰��� ���� �¼����� ������ �� �����ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
                                
                            	return;
                            }
                        }
                    }
                    
                    String setOr = "ticket.showScheduleId="+sche;
                    String whereOr = "ticket.payId = "+inputPayId_IN;
                	db1.update("ticket", setOr, whereOr);
                	setOr = "ticket.roomId ="+room;
                	db1.update("ticket", setOr, whereOr);
                	
                	List<String> result = db1.showDetailPayment(inputPayId_IN);
                	listModel.clear();
                	for (String item : result) {
                        if (!listModel.contains(item)) {
                            listModel.addElement(item);
                        }
                    }
                }
	        }

	        private void changeScheduleAction(ActionEvent e) {
	            try {
	            	int preschID = db1.scheduleIdFromPayid(inputPayId_IN);
	            	int premovieId = db1.movieIdFromscheduleId(preschID);
	            	int schID = Integer.parseInt(timechangeField.getText().strip());
		        	int room = db1.roomIdFromscheduleId(schID);
		        	int movieID = db1.movieIdFromscheduleId(schID);
		        	if(premovieId != movieID) {
		        		JOptionPane.showMessageDialog(null, "���� ��ȭ�� ������ ���� �ƴմϴ�.", "error", JOptionPane.ERROR_MESSAGE);
		        		return;
		        	}
	                List<String> seatResult = db1.seatBySchedule(schID);
	                String limit = seatResult.removeFirst();
	                String [] limitParts = limit.split(",", 2);
	                int rc = Integer.parseInt(limitParts[0].strip());
	                int rr = Integer.parseInt(limitParts[1].strip());
	                
	                limit = seatResult.removeFirst();
                    limitParts = limit.split(" ", 2);
                    boolean isAlreadyBooked = false;
    		        List<String> checkSeat = db1.showDetailPayment(inputPayId_IN);
    		        for (String item : checkSeat) {
                        if (item.contains("seat")) {
                            String [] loc = item.split(" : ");
                            String realL = loc[1];
                            String [] c_r = realL.split("-");
                            
                            int c = Integer.parseInt(c_r[0].strip());
                            int r = Integer.parseInt(c_r[1].strip());
                            
                            for (int i = 0; i<limitParts.length;i++) {
                            	realL = limitParts[i];
                            	c_r = realL.split("-");
                            	int lC = Integer.parseInt(c_r[0].strip());
                            	int lR = Integer.parseInt(c_r[1].strip());
                            	
                            	if(c == lC && r == lR) {
                            		isAlreadyBooked = true;
                            		break;
                            	}
                            }
                            if (c>rc || r>rr || isAlreadyBooked) {
                            	JOptionPane.showMessageDialog(null, "���� ���� �󿵰��� ���� �¼����� ������ �� �����ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
                                
                            	return;
                            }
                        }
                    }
	                
	                String setOr = "ticket.showScheduleId="+schID;
	                String whereOr = "ticket.payId = "+inputPayId_IN;
	              	if(db1.update("ticket", setOr, whereOr)<0) {
	              		JOptionPane.showMessageDialog(null, "�߸��� ���Դϴ�.", "error", JOptionPane.ERROR_MESSAGE);
	              	}
	              	setOr = "ticket.roomId ="+room;
	              	db1.update("ticket", setOr, whereOr);
	              	
	              	List<String> result = db1.showDetailPayment(inputPayId_IN);
	              	listModel.clear();
	              	for (String item : result) {
	                      if (!listModel.contains(item)) {
	                          listModel.addElement(item);
                    }
	            }
	          }
	          catch(NumberFormatException e1) {
	        	  JOptionPane.showMessageDialog(null, "������ ID ���� �Է����ּ���.", "error", JOptionPane.ERROR_MESSAGE);
	          }
	        }

	        private void deleteReservationAction(ActionEvent e) {
	        	int result = db1.cancelPay(inputPayId_IN);
                if (result<0) {
                	JOptionPane.showMessageDialog(null, "�����Ϳ� �߸��� ���� �ֽ��ϴ�.", "error", JOptionPane.ERROR_MESSAGE);
                }
                else {
                	dispose();
                }
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * SwingUtilities.invokeLater(new Runnable() { public void run() { new
		 * AdminUI(); } });
		 */
		ui uiInstance = new ui();
		//AdminUI aInstance = uiInstance.new AdminUI();
		LoginUI aInstance = uiInstance.new LoginUI();
		//DetailedreservationUI aInstance = uiInstance.new DetailedreservationUI();
		//new UsermainUI();
		//new ReservationUI();
		//new DetailedreservationUI();
	}

}
