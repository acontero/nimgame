import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Button;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.UIManager;


public class GUI extends JFrame {
	
	private JPanel contentPane;

	int upper = 7;
	int lower = 1;
	int stack1 = (int)(Math.random() * (upper - lower)) + lower;
	int stack2 = (int)(Math.random() * (upper - lower)) + lower;
	int stack3 = (int)(Math.random() * (upper - lower)) + lower;

	private Button[] buttonList1 = new Button[stack1];
	private Button[] buttonList2 = new Button[stack2];
	private Button[] buttonList3 = new Button[stack3];
	private long startTime,endTime;
	
	private int[] computerMove;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//This function will generate and push buttons into a grid
	public void generateStacks(int stackNum1, int stackNum2, int stackNum3, JPanel panelStack){
		//stack 1
		for (int i = 0; i < stackNum1; i++){
			Button button = new Button(""+(i+1));
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 1;
			gbc_button.gridy = 20-i;
			buttonList1[i] = button;
			panelStack.add(buttonList1[i], gbc_button);
		}
		//stack 2
		for (int i = 0; i < stackNum2; i++){
			Button button = new Button(""+(i+1));
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 3;
			gbc_button.gridy = 20-i;
			buttonList2[i] = button;
			panelStack.add(buttonList2[i], gbc_button);
		}
		//stack 3
		for (int i = 0; i < stackNum3; i++){
			Button button = new Button(""+(i+1));
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 4;
			gbc_button.gridy = 20-i;
			buttonList3[i] = button;
			panelStack.add(buttonList3[i], gbc_button);
		}
	}
	
	public void deleteTokenFromStack(int stackNum, int numOfTokens, JPanel panelStack){
		
		if(stackNum == 1){
			//remove from stack 1
			for(int i = stack1-1; i > (stack1-1)-numOfTokens; i--){
				panelStack.remove(buttonList1[i]);
			}
			stack1 -= numOfTokens;
			
		}
		else if(stackNum == 2){
			//remove from stack 2
			for(int i = stack2-1; i > (stack2-1)-numOfTokens; i--){
				panelStack.remove(buttonList2[i]);
			}
			stack2 -= numOfTokens;
			
		}
		else{
			//remove from stack 3
			for(int i = stack3-1; i > (stack3-1)-numOfTokens; i--){
				panelStack.remove(buttonList3[i]);
			}
			stack3 -= numOfTokens;
			
		}
		
	}
	
	public void endOfGame(int result,JButton btnMakeMove){
		if(result == 1){
			JOptionPane.showMessageDialog(null, "You win");
		}
		else{
			JOptionPane.showMessageDialog(null, "You lose");
		}
		System.exit(0);
	}
	
	
	/**
	 * Create the frame.
	 */
	public GUI() {
				
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 473);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final JPanel panelStack = new JPanel();
		panelStack.setBackground(new Color(238, 238, 238));
		
		final JSpinner spinnerStack = new JSpinner();
		
		final JSpinner spinnerTokens = new JSpinner();
		
		JLabel lblChooseStack = new JLabel("Choose Stack");
		
		JLabel lblNewLabel = new JLabel("Choose Tokens");
		
		final JButton btnMakeMove = new JButton("Make Move");
		final JLabel lblTurn = new JLabel("Play Nim Against A Monkey!");
		lblTurn.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		
		
		//BUTTON EVENT LISTENER!!!
		//Should grab values from the spinners and use them!
		btnMakeMove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int stackNumber = (Integer) spinnerStack.getValue();
				int tokenNumber = (Integer) spinnerTokens.getValue();
				if(stackNumber == 1 && tokenNumber > stack1 || stackNumber == 2 && tokenNumber > stack2
						|| stackNumber == 3 && tokenNumber > stack3){
					JOptionPane.showMessageDialog(null, "You are taking the wrong amount of tokens");
					return;
				}
				else if(stackNumber > 3 || stackNumber < 0){
					JOptionPane.showMessageDialog(null, "You are picking not existing stacks");
					return;
				}
				else if(stackNumber == 0 || tokenNumber == 0){
					JOptionPane.showMessageDialog(null, "Either you didn't choose a stack or you don't want to take any tokens");
					return;
				}
				else{
					deleteTokenFromStack(stackNumber,tokenNumber,panelStack);
				}
				if(stack1==0 && stack2 == 0 && stack3 == 0){
					endOfGame(1,btnMakeMove);
				}
				//Then the computer goes
				
				try{
					Thread.sleep(1000*3);
				}catch(InterruptedException e){
					System.out.println("Computer committed suicide");
				}
				
				
				startTime = System.currentTimeMillis();
				
				//Using the optimal decision
				//Strategy 1
				computerMove = OptimalDecision.computerMove(new int [] {stack1,stack2,stack3});
				
				//Strategy 2
				//computerMove = OptimalDecision.computerMove2(new int [] {stack1,stack2,stack3});
				
				deleteTokenFromStack(computerMove[0],computerMove[1],panelStack);
				endTime = System.currentTimeMillis();
				
				
				if(stack1==0 && stack2 == 0 && stack3 == 0){
					endOfGame(0,btnMakeMove);
				}
				
			}
		});
		
		JEditorPane dtrpnObjectiveYourObjective = new JEditorPane();
		dtrpnObjectiveYourObjective.setBackground(UIManager.getColor("Button.background"));
		dtrpnObjectiveYourObjective.setText("Objective: Your Objective is to leave your opponent with no muffins to choose fom any of the three stacks. The person that can't take a muffin loses.                                   \n\nUsing the spinners select a stack and the number of muffins you want to remove from that stack. ");
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelStack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnMakeMove)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(lblChooseStack))
										.addComponent(spinnerStack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(spinnerTokens, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(60))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(100)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(dtrpnObjectiveYourObjective, GroupLayout.PREFERRED_SIZE, 295, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblTurn)
									.addGap(5)))))
					.addGap(108))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblTurn)
							.addGap(18)
							.addComponent(dtrpnObjectiveYourObjective, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblChooseStack)
								.addComponent(lblNewLabel))
							.addGap(17)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(spinnerStack, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerTokens, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(panelStack, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addComponent(btnMakeMove)
					.addGap(27))
		);
		GridBagLayout gbl_panelStack = new GridBagLayout();
		
		gbl_panelStack.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelStack.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelStack.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelStack.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelStack.setLayout(gbl_panelStack);
		generateStacks(stack1,stack2,stack3,panelStack);
		contentPane.setLayout(gl_contentPane);
	}
}
