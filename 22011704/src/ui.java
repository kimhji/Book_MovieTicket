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
	        c.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50)); // 세로로 정렬

	        AdminButton = new JButton("관리자 로그인");
	        UserButton = new JButton("사용자 로그인");
	        // AdminButton의 ActionListener
	        AdminButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//관리자 UI 오픈 
	            	 AdminUI adminUI = new AdminUI();
	                 adminUI.setVisible(true);
	            }
	        });

	        // UserButton의 ActionListener
	        UserButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//사용자 UI 오픈 
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
		//콤보 박스 선택하면 화면 비율 달라지는거 조정해야됨 
		class AdminUI extends JFrame  implements ActionListener {
		    private JTextField conditionTextField, conditionTableField;
		    private JButton deleteButton, updateButton, resetButton, retrieveButton, insertButton;
		    private JComboBox<String> tableComboBox;
		    private JTextField[] inputFields;
		    private JLabel[] inputLabels;
		    private JPanel inputPanel;
		    private GridBagConstraints gbc = new GridBagConstraints();

		    private String[][] tableAttributes = { //&여기 연결 
		            {"영화명", "영화등급", "감독", "배우", "장르", "영화 줄거리", "개봉일", "평점"},
		            {"좌석 가로 수", "좌석 세로 수", "사용 중"},
		            {"영화 번호", "상영관 번호", "상영일", "요일", "회차", "시작 시간"},
		            {"사용자ID", "사용자 이름", "전화번호", "이메일"},
		            {"결제 방식","결제여부","총 금액", "사용자 ID", "예매 날짜"},
		            {"상영관 번호", "좌석사용여부", "좌석 x좌표", "좌석 y좌표", "상영일정번호"},
		            {"상영일정번호", "상영관 번호", "좌석 번호", "예매 번호", "발권 여부", "표준가격", "판매가격"}
		    };

		    AdminUI() {
		        setTitle("Admin Interface");
		        setSize(800, 500);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		        setLayout(new GridBagLayout());

		        JPanel conditionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        JLabel conditionLabel = new JLabel("조건식:");
		        conditionTextField = new JTextField(15);
		        JLabel conditionTableLabel = new JLabel("테이블:");
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
		        
		        // JList 생성 및 데이터 모델 설정
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

		        deleteButton = new JButton("삭제");
		        updateButton = new JButton("변경");
		        resetButton = new JButton("초기화");
		        retrieveButton = new JButton("전체 테이블 조회");
		        
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
		            			JOptionPane.showMessageDialog(null, "조건식 혹은 테이블에 잘못된 값이 들어가 있습니다.", "error", JOptionPane.ERROR_MESSAGE);
		            		}
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "조건식과 테이블을 빈칸으로 할 수 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
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
		            			JOptionPane.showMessageDialog(null, "조건식 혹은 테이블에 잘못된 값이 들어가 있습니다.", "error", JOptionPane.ERROR_MESSAGE);
		            		}
		            	}
		            	else {
		            		JOptionPane.showMessageDialog(null, "조건식과 테이블을 빈칸으로 할 수 없으며 조건식은 $를 통해 set조건과 where 조건을 나누어 작성해야 합니다.", "error", JOptionPane.ERROR_MESSAGE);
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

		        tableComboBox = new JComboBox<>(new String[]{"영화", "상영관", "상영 일정", "회원", "예매", "좌석", "티켓"});
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


		        insertButton = new JButton("입력");
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
		                    	JOptionPane.showMessageDialog(null, "입력 시 빈칸이 없어야 합니다.", "error", JOptionPane.ERROR_MESSAGE);
		                    	return;
		                    }
		                }
		                String tabName = tableComboBox.getSelectedItem().toString();
		                String colString = "(";
		                String inpTab = "";
		                switch (tabName) {
			                case "영화":
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
			                case "상영관":
			                	colString += values[0]+", ";
			                	colString += values[1]+", ";
			                	colString += values[2]+")";
			                	inpTab = "room";
			                	break;
			                case "상영 일정":
			                	colString += values[0]+", ";
			                	colString += values[1]+", ";
			                	colString += "\'"+values[2]+"\', ";
			                	colString += "\'"+values[3]+"\', ";
			                	colString += values[4]+", ";
			                	colString += "\'"+values[5]+"\')";
			                	inpTab = "showSchedule";
			                	break;
			                case "회원":
			                	colString += "\'"+values[0]+"\', ";
			                	colString += "\'"+values[1]+"\', ";
			                	colString += "\'"+values[2]+"\', ";
			                	colString += "\'"+values[3]+"\')";
			                	inpTab = "userInfo";
			                	break;
			                case "예매":
			                	colString += "\'"+values[0]+"\', ";
			                	colString += values[1]+", ";
			                	colString += values[2]+", ";
			                	colString += "\'"+values[3]+"\', ";
			                	colString += "\'"+values[4]+"\')";
			                	inpTab = "payInfo";
			                	break;
			                case "좌석":
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
			                        	JOptionPane.showMessageDialog(null, "정해진 좌석 수를 벗어나는 좌석 좌표를 입력했습니다.", "error", JOptionPane.ERROR_MESSAGE);
			                        	return;
			                        }
			                    }
			                    catch (NumberFormatException ex){
			                    	JOptionPane.showMessageDialog(null, "입력 값에 잘못된 값이 있습니다.", "error", JOptionPane.ERROR_MESSAGE);
			                        return;
			                    }
			                	break;
			                case "티켓":
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
		                	JOptionPane.showMessageDialog(null, "입력 값에 잘못된 값이 있습니다.", "error", JOptionPane.ERROR_MESSAGE);
		                }
		            }
		        });

		        setVisible(true);
		    }

		    void createInputFields(String[] attributes) {
		        if (inputPanel == null) {
		            inputPanel = new JPanel();
		            inputPanel.setLayout(new GridBagLayout());
		            // 기타 초기화 작업
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

		        // 입력 필드와 라벨 추가
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

		        revalidate(); // 프레임을 다시 유효화하여 변경 사항을 적용
		        repaint(); // 화면을 다시 그려서 변경 사항을 표시
		        
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
		        setLocationRelativeTo(null); // 화면 중앙에 위치

		        // 전체 UI 패널
		        JPanel mainPanel = new JPanel(new BorderLayout());
		        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // 전체 페이지에 여백 추가

		        // 상단 패널 (예매일, 예매 내역 확인 버튼)
		        JPanel topPanel = new JPanel(new BorderLayout());
		        JPanel topdatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		        JButton confirmBookingButton = new JButton("예매 내역 확인");
		        topPanel.add(topdatePanel, BorderLayout.WEST);
		        topPanel.add(confirmBookingButton, BorderLayout.EAST);
		        mainPanel.add(topPanel, BorderLayout.NORTH);
		        
		        confirmBookingButton.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {
		            	List<String> result = db1.checkMyPayment();
		            	if(result.size()==0) {
		            		JOptionPane.showMessageDialog(null, "예매 내역이 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
		            	}
		            	else {
		            	 ReservationUI reservationUI = new ReservationUI();
		                 reservationUI.setVisible(true);
		            	}
		            }
		        });
		        // 왼쪽 패널 (검색 영역)
		        JPanel leftPanel = new JPanel(new BorderLayout());
		        JPanel searchPanel = new JPanel();
		        searchPanel.setLayout(new GridBagLayout());
		        GridBagConstraints gbc = new GridBagConstraints();
		        gbc.insets = new Insets(5, 5, 5, 5); // 여백 설정

		        // 영화명 패널
		        JLabel movieLabel = new JLabel("영화명: ");
		        JTextField movieField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy = 0;
		        searchPanel.add(movieLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(movieField, gbc);

		        // 감독명 패널
		        JLabel directorLabel = new JLabel("감독명: ");
		        JTextField directorField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy++;
		        searchPanel.add(directorLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(directorField, gbc);

		        // 배우 패널
		        JLabel actorLabel = new JLabel("배우: ");
		        JTextField actorField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy++;
		        searchPanel.add(actorLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(actorField, gbc);

		        // 장르 패널
		        JLabel genreLabel = new JLabel("장르: ");
		        JTextField genreField = new JTextField(18);
		        gbc.gridx = 0;
		        gbc.gridy++;
		        searchPanel.add(genreLabel, gbc);
		        gbc.gridx = 1;
		        searchPanel.add(genreField, gbc);

		        leftPanel.add(searchPanel, BorderLayout.CENTER);

		        // 검색 버튼 패널
		        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		        JButton searchButton = new JButton("선택 조회");
		        JButton searchAllButton = new JButton("전체 조회");
		        buttonPanel.add(searchButton);
		        buttonPanel.add(searchAllButton);
		        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

		        mainPanel.add(leftPanel, BorderLayout.WEST);

		        // 오른쪽 패널 (데이터 테이블)
		        listModel = new DefaultListModel<>();
		        
		        // JList 생성 및 데이터 모델 설정
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
		        
		        //선택 조회 
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
		                if (e.getClickCount() == 2) { // 클릭을 2번 했을 때
		                    int index = list.locationToIndex(e.getPoint());                	

		                    if (index >= 0) {
		                        String selectedItem = listModel.getElementAt(index);

		                        String[] parts = selectedItem.split("\\|"); //|로 구분되었으므로 
		                        int result = db1.scheduleIdFromMovieId(Integer.parseInt(parts[1].trim()));
		                        if (result < 0) {
		                        	JOptionPane.showMessageDialog(null, "해당 영화의 상영 일정이 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
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
		        // 좌석 예매 UI를 새로운 창으로 띄웁니다.
		    	List<String> replySeat = db1.seatBySchedule(scheduleIdInput);
		    	String reserved = replySeat.removeLast();
		    	List<String> reservedSeats = Arrays.asList(reserved.split(" "));//&여기 연결 
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
		        this.setPreferredSize(new Dimension(50, 50)); // 정사각형 버튼 크기 설정
		    }
		    public String getSeatNumber() {
		        return seatNumber;
		    }
		    public void select() {
		        if (!isReserved) {
		            isSelected = true;
		            setBackground(Color.PINK); // 선택된 좌석의 색상 변경
		        }
		    }

		    public void deselect() {
		        if (!isReserved) {
		            isSelected = false;
		            setBackground(null); // 기본색으로 되돌림
		        }
		    }
		    public void reserve() {
		        isReserved = true;
		        setEnabled(false);
		        setBackground(Color.darkGray); // 예약된 좌석의 색상 변경
		    }

		    public void unreserve() {
		        isReserved = false;
		        setEnabled(true);
		        setBackground(null); // 기본색으로 되돌림
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
		        selectedSeatLabel = new JLabel("선택한 좌석: 없음");
		        selectedSeatLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
		        JButton bookButton = new JButton("예매하기");
		        bookButton.setPreferredSize(new Dimension(150, 50));
		        JButton checkButton = new JButton("확인");
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
		        StringBuilder seatsText = new StringBuilder("선택한 좌석: ");
		        JPanel seatPanelWrapper = (JPanel) getContentPane().getComponent(0); // seatPanelWrapper 가져오기
		        JPanel seatPanel = (JPanel) seatPanelWrapper.getComponent(0); // seatPanel 가져오기

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
		            selectedSeatLabel.setText("선택한 좌석: 없음");
		        } else {
		            selectedSeatLabel.setText(seatsText.toString());
		        }
		    }

		    private void bookSeats() {
		        if (selectedSeats.isEmpty()) {
		            JOptionPane.showMessageDialog(this, "선택된 좌석이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
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
		            JOptionPane.showMessageDialog(this, "좌석이 예매되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		}

		class ReservationUI extends JFrame {
		     // 리스트 모델 설정
			 DefaultListModel<String> listModel = new DefaultListModel<>();
		     // 리스트 생성 및 모델 할당
		     JList<String> list = new JList<>(listModel);

		    public ReservationUI() {
		        // 프레임 설정
		        setTitle("예매 내역");
		        setSize(800, 500);
		        setLocationRelativeTo(null);
		        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		        // 스크롤 패널에 리스트 추가
		        JScrollPane scrollPane = new JScrollPane(list);

		        // 패널 생성 및 테두리 설정
		        JPanel panel = new JPanel(new BorderLayout());
		        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        panel.add(scrollPane, BorderLayout.CENTER);

		        // 프레임에 패널 추가
		        add(panel, BorderLayout.CENTER);
		        setVisible(true);
		        List<String> result = db1.checkMyPayment();
            	listModel.clear();
            	for (String item : result) {
                    if (!listModel.contains(item)) {
                        listModel.addElement(item);
                    }
                }
            	//ListModel 변화 리스너
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
            	
		        // 예매 항목 클릭 리스너
		        list.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent e) {
		                if (e.getClickCount() == 1) {
		                    int selectedIndex = list.getSelectedIndex();
		                    if (selectedIndex != -1) {
		                        // 예매 내역에서 선택된 항목의 정보를 가져오는 코드 
		                        String selectedItem = listModel.getElementAt(selectedIndex);
		                        //|로 구분, 임시 +++
		                        String[] parts = selectedItem.split("\\|");
		                        
		                        try{
		                        	int payIdSelected = Integer.parseInt(parts[1].strip());
		                        	DetailedreservationUI detailedReservationUI = new DetailedreservationUI(payIdSelected, listModel);
		                        	detailedReservationUI.setVisible(true);
			                    }
			                    catch (NumberFormatException ex){
			                    	JOptionPane.showMessageDialog(null, "데이터에 잘못된 값이 있습니다.", "error", JOptionPane.ERROR_MESSAGE);
			                        return;
			                    }
		                        
		                    }
		                }
		            }
		        });
		    }

		    // 예매 내역을 리스트에 추가하는 메서드 +++ 이거 현지가 구현해줌 
		    public void addReservation(String movieName, String screeningDate, String theaterNumber, String seatNumber, String price) {
		        String reservationInfo = movieName + " - " + screeningDate + " - " + theaterNumber + " - " + seatNumber + " - " + price;
		        listModel.addElement(reservationInfo);
		    }

		    // 예매 내역의 상세 정보를 표시하는 메서드 (실제 구현은 생략되었습니다)
		    private void showDetailedInfo(String movieName, String screeningDate, String theaterNumber, String seatNumber, String price) {
		        // 여기에 상세 정보를 표시하는 로직을 구현할 수 있습니다.
		        JOptionPane.showMessageDialog(this,
		                "영화명: " + movieName + "\n상영일: " + screeningDate + "\n상영관번호: " + theaterNumber
		                        + "\n좌석번호: " + seatNumber + "\n판매 가격: " + price,
		                "예매 상세 정보", JOptionPane.INFORMATION_MESSAGE);
		    }

		}
		
		class DetailedreservationUI extends JFrame {

	        public JTextField moviechangeField;
	        public JTextField timechangeField;
	        private JComboBox<String> comboBox;
	     // 리스트 모델 설정
			DefaultListModel<String> listModel = new DefaultListModel<>();
		     // 리스트 생성 및 모델 할당
		    JList<String> list = new JList<>(listModel);
		    int inputPayId_IN = -1;
	        private int selectedReservationIndex = -1; // 이용해서 조회한거 저장 
	        private Vector<Object[]> detailedData; // 예매 정보를 저장하는 벡터 => stinglist
		    public DetailedreservationUI(int inputPayId, DefaultListModel<String> inputList) {
		    	comboBox = new JComboBox<>(db1.getMovieList());
		    	
		    	inputPayId_IN = inputPayId;
		        setTitle("예매 시스템");
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

		        // 패널 생성 및 테두리 설정
		        JPanel panel = new JPanel(new BorderLayout());
		        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		        panel.add(scrollPane, BorderLayout.CENTER);

		        // 프레임에 패널 추가
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
		                	JOptionPane.showMessageDialog(null, "데이터에 잘못된 값이 있습니다.", "error", JOptionPane.ERROR_MESSAGE);
		                }
		                dispose();
		            }
		        });*/
		        //detailedData = new Vector<>();
		        initializeUI();
		    }

	        private void initializeUI() {
	            // 왼쪽 패널 (리스트)
	            JPanel leftPanel = new JPanel(new BorderLayout());
	            list = new JList<>(listModel);
	            JScrollPane scrollPane = new JScrollPane(list);
	            leftPanel.add(scrollPane, BorderLayout.CENTER);

	            
	            // 오른쪽 패널 (검색 필드와 버튼들)
	            JPanel rightPanel = new JPanel();
	            rightPanel.setLayout(new GridBagLayout());
	            GridBagConstraints gbc = new GridBagConstraints();
	            gbc.insets = new Insets(5, 5, 5, 5);
	              
	            JButton checkButton = new JButton("확인");
	            JButton changeMovieButton = new JButton("영화 변경");
	            JButton changeScheduleButton = new JButton("일정 변경");
	            JButton deleteReservationButton = new JButton("예매 삭제");

	            checkButton.addActionListener(this::searchAction);
	            changeMovieButton.addActionListener(this::changeMovieAction);
	            changeScheduleButton.addActionListener(this::changeScheduleAction);
	            deleteReservationButton.addActionListener(this::deleteReservationAction);
	            
	            

	         // 변경 날짜 및 영화 필드 추가
	            JLabel moviechangeLabel = new JLabel("변경 영화:");
	            moviechangeField = new JTextField(10);
	            JLabel timechangeLabel = new JLabel("변경 날짜:");
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
	              // 여백 추가
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
                	JOptionPane.showMessageDialog(null, "해당 영화의 상영 일정이 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
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
                            	JOptionPane.showMessageDialog(null, "변경 후의 상영관에 기존 좌석으로 예매할 수 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
                                
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
		        		JOptionPane.showMessageDialog(null, "같은 영화의 스케쥴 값이 아닙니다.", "error", JOptionPane.ERROR_MESSAGE);
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
                            	JOptionPane.showMessageDialog(null, "변경 후의 상영관에 기존 좌석으로 예매할 수 없습니다.", "error", JOptionPane.ERROR_MESSAGE);
                                
                            	return;
                            }
                        }
                    }
	                
	                String setOr = "ticket.showScheduleId="+schID;
	                String whereOr = "ticket.payId = "+inputPayId_IN;
	              	if(db1.update("ticket", setOr, whereOr)<0) {
	              		JOptionPane.showMessageDialog(null, "잘못된 값입니다.", "error", JOptionPane.ERROR_MESSAGE);
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
	        	  JOptionPane.showMessageDialog(null, "스케쥴 ID 값을 입력해주세요.", "error", JOptionPane.ERROR_MESSAGE);
	          }
	        }

	        private void deleteReservationAction(ActionEvent e) {
	        	int result = db1.cancelPay(inputPayId_IN);
                if (result<0) {
                	JOptionPane.showMessageDialog(null, "데이터에 잘못된 값이 있습니다.", "error", JOptionPane.ERROR_MESSAGE);
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
