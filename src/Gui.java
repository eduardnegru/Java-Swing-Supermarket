import com.sun.org.apache.regexp.internal.RE;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Gui extends JFrame {

    private static final String storePath = "store.txt";
    private static final String customersPath = "customers.txt";
    private static final String eventsPath = "events.txt";
    private static final int nrDepartments = 4;

    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel root = new JPanel();
    private JPanel sPanel = new JPanel();
    private JPanel wPanel = new JPanel();
    private JPanel backPanel = new JPanel();
    private JPanel backPanel2 = new JPanel();
    private JPanel backPanel3 = new JPanel();
    private JPanel backPanel4= new JPanel();
    private JPanel oneDepartmentPanel = new JPanel();
    private JPanel menu = new JPanel();

    private JPanel clientTitlePanel = new JPanel();
    private JButton backButton = new JButton("OK");
    private JButton backButton2 = new JButton("OK");
    private JButton backButton4 = new JButton("OK");
    private JButton ok2 = new JButton("EXIT");
    private JButton departmentButton = new JButton("");
    private JButton purchaseButton = new JButton();
    private JButton notificationButton = new JButton();
    private JButton shoppingButton = new JButton();
    private JButton addButton = new JButton();
    private JButton deleteButton = new JButton();
    private JButton wishButton = new JButton();
    private JButton goToShoppingButton = new JButton();
    private JButton goTowishButton = new JButton();
    private CardLayout cardLayout = new CardLayout();

    private JLabel title = new JLabel("Welcome to the Supermarket App");
    private JLabel chooseFiles = new JLabel("Please enter customers.txt and store.txt");
    private JLabel nameLabel = new JLabel("Name:");
    private JLabel idLabel = new JLabel("ID:");
    private JLabel delLabel = new JLabel("ID:");
    private JLabel priceLabel = new JLabel("Price:");
    private JLabel deptLabel = new JLabel("DepartmentID:");
    private JLabel clientLabel = new JLabel("Choose a client");
    private JLabel nLabel = new JLabel();
    private JLabel itemNameLabel = new JLabel("Choose the product:");
    private JLabel budgetLabel = new JLabel("Budget: ");
    private JLabel totalLabel = new JLabel("Total: ");
    private JLabel wishNameLabel = new JLabel();
    private JLabel observersLabel = new JLabel("Observers");
    private JLabel customersLabel = new JLabel("Custormers");
    private JLabel []labels = new JLabel[12];


    private JPanel adminPanel = new JPanel();
    private JPanel h = new JPanel();
    private JPanel labelPanel = new JPanel();
    private JPanel backPanel5 = new JPanel();
    private JPanel wishNamePanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private JPanel listPanel = new JPanel();
    private JPanel titlePanel = new JPanel();
    private JPanel browsePanel = new JPanel();
    private JPanel ePanel = new JPanel();
    private JPanel radios = new JPanel();
    private JPanel sortPanel = new JPanel();
    private JPanel clientPanel = new JPanel();
    private JPanel tableButtonPanel = new JPanel();
    private JPanel peoplePanel = new JPanel();
    private JPanel peoplePanel2 = new JPanel();
    private JPanel peoplePanel3 = new JPanel();
    private JPanel peoplePanel4 = new JPanel();
    private JPanel inputPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JPanel deletePanel = new JPanel();
    private JPanel addItemPanel = new JPanel();
    private JPanel departmentPanel = new JPanel();
    private JPanel okPanel = new JPanel();
    private JPanel notificationPanel = new JPanel();
    private JPanel nAuxPanel = new JPanel();
    private JPanel infoPanel = new JPanel();
    private JPanel ideaPanel = new JPanel();
    private JPanel wishButtonPanel = new JPanel();
    private JPanel addWishPanel = new JPanel();
    private JPanel department1Panel = new JPanel();
    private JPanel department2Panel = new JPanel();
    private JPanel department3Panel = new JPanel();
    private JPanel department4Panel = new JPanel();
    private JPanel loginPanel = new JPanel();


    private JButton browseButton;
    private JButton exitButton;
    private JButton okButton;
    private JButton sortAlpha;
    private JButton sortNum;
    private JButton addTable;
    private JButton delTable;
    private JButton suggestButton;
    private JButton addWishButton;
    private JButton deleteWishButton;
    private JButton backButton3;
    private JButton backButton5;

    private JTextField nameField = new JTextField(10);
    private JTextField idField = new JTextField(10);
    private JTextField priceField = new JTextField(10);
    private JTextField deptField = new JTextField(10);
    private JTextField delField = new JTextField(10);
    private JTextField budgetField = new JTextField(10);
    private JTextField totalField = new JTextField(10);
    private JTextField notificationField = new JTextField(10);

    private boolean enabledOK = false;
    private JLabel browseLabel = new JLabel("Browse files");
    private JLabel notificationLabel = new JLabel();
    private ButtonGroup group = new ButtonGroup();
    private JRadioButton adminButton = new JRadioButton();
    private JRadioButton clientButton = new JRadioButton();

    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel sModel = new DefaultTableModel();
    private DefaultTableModel wModel = new DefaultTableModel();

    private JTable table = new JTable(model);
    private JTable sTable = new JTable(sModel);
    private JTable wTable = new JTable(sModel);

    private JScrollPane jScrollPane = new JScrollPane(table);
    private JScrollPane sScroll = new JScrollPane(sTable);
    private JScrollPane wScroll = new JScrollPane(wTable);
    private JPasswordField pass = new JPasswordField(15);

    public static HashMap<String, Customer> customers;
    public static Store store;
    private static Color BLUE = new Color(255,255,255);

    private DefaultListModel listModel = new DefaultListModel();
    private DefaultListModel modelList = new DefaultListModel();

    private JList<Customer> customerList = new JList<>(listModel);
    private JList<Notification> notificationList = new JList<>(modelList);
    private JList<Customer> observers1List = new JList<>();
    private JList<Customer> clients1List = new JList<>();
    private JList<Customer> observers2List = new JList<>();
    private JList<Customer> clients2List = new JList<>();
    private JList<Customer> observers3List = new JList<>();
    private JList<Customer> clients3List = new JList<>();
    private JList<Customer> observers4List = new JList<>();
    private JList<Customer> clients4List = new JList<>();

    private JScrollPane scrollLIst = new JScrollPane(customerList);
    private JScrollPane observers1Scroll = new JScrollPane(observers1List);
    private JScrollPane clients1Scroll = new JScrollPane(clients1List);
    private JScrollPane observers2Scroll = new JScrollPane(observers2List);
    private JScrollPane clients2Scroll = new JScrollPane(clients2List);
    private JScrollPane observers3Scroll = new JScrollPane(observers3List);
    private JScrollPane clients3Scroll = new JScrollPane(clients3List);
    private JScrollPane observers4Scroll = new JScrollPane(observers4List);
    private JScrollPane clients4Scroll = new JScrollPane(clients4List);

    private JComboBox<Item> comboBox = new JComboBox<>();
    private JComboBox<Item> comboBox2 = new JComboBox<>();

    private int ok = 0;
    private boolean alreadySortedAlpha = false;
    public boolean isSelected = false;
    private String clientName = "";
    String []columnNames = {"Name", "ID", "Price"};
    String []columnNames2 = {"ID", "Name", "Price", "DepartmentID"};
    ArrayList<Customer> cust;
    ArrayList<Item> shoppingItems;
    ArrayList<Item> wishItems;
    ArrayList<Integer> popular = new ArrayList<>();
    ArrayList<Integer> wanted = new ArrayList<>();
    Integer []expensive = new Integer[4];
    int indexLabel = 0;



    public Gui(){

        setTitle("SuperMarket");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(900,800));
        root.setLayout(cardLayout);

        model.setColumnIdentifiers(columnNames);
        sModel.setColumnIdentifiers(columnNames2);
        wModel.setColumnIdentifiers(columnNames2);

        backButton = setButton("arrow.png");
        backButton2 = setButton("arrow.png");
        backButton3 = setButton("arrow.png");
        backButton4 = setButton("arrow.png");
        backButton5 = setButton("close.png");
        browseButton = setButton("folders.png");
        exitButton = setButton("exit.png");
        okButton = setButton("ok.png");
        departmentButton = setButton("store.png");
        goTowishButton = setButton("wish.png");
        goToShoppingButton = setButton("shopping.png");
        goToShoppingButton.setText("Go to ShoppingCart   ");
        goTowishButton.setText("Go to WishList   ");
        exitButton.setText("Exit");
        okButton.setText("Ok");

        backPanel.setLayout(new BorderLayout());
        backPanel2.setLayout(new BorderLayout());
        backPanel3.setLayout(new BorderLayout());
        backPanel4.setLayout(new BorderLayout());
        backPanel5.setLayout(new BorderLayout());


        backPanel.add(backButton, BorderLayout.WEST);
        backPanel.add(goTowishButton,BorderLayout.EAST);
        backPanel2.add(backButton2, BorderLayout.WEST);
        backPanel4.add(backButton4, BorderLayout.WEST);
        backPanel5.add(backButton5, BorderLayout.CENTER);
        backPanel4.add(departmentButton, BorderLayout.EAST);
        backPanel3.add(goToShoppingButton, BorderLayout.EAST);
        backPanel3.add(backButton3, BorderLayout.WEST);

        departmentButton.setText("Visit Departments  ");

        paintRootScreen();

/**ADMIN SCRENN*/

        adminPanel.setLayout(new BoxLayout(adminPanel, BoxLayout.Y_AXIS));
        sortPanel.setLayout(new FlowLayout());
        tableButtonPanel.setLayout(new FlowLayout());
        inputPanel.setLayout(new FlowLayout());
        deletePanel.setLayout(new FlowLayout());
        departmentPanel.setLayout(null);

        sortAlpha = setButton("alpha.png");
        sortNum = setButton("num.png");
        addTable = setButton("addTable.png");
        delTable = setButton("deleteTable.png");
        addTable.setText("Add Product");
        delTable.setText("Delete Product");

        sortPanel.add(sortAlpha);
        sortPanel.add(Box.createHorizontalStrut(20));
        sortPanel.add(sortNum);

        deletePanel.add(delLabel);
        deletePanel.add(delField);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(deptLabel);
        inputPanel.add(deptField);
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);

        tableButtonPanel.add(addTable);
        tableButtonPanel.add(Box.createHorizontalStrut(20));
        tableButtonPanel.add(delTable);

        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.setModel(model);
        adminPanel.add(Box.createVerticalStrut(40));
        adminPanel.add(sortPanel);
        adminPanel.add(Box.createVerticalStrut(50));
        adminPanel.add(jScrollPane);
        adminPanel.add(Box.createVerticalStrut(50));
        adminPanel.add(tableButtonPanel);

        adminPanel.add(backPanel4);
        backPanel.setPreferredSize(new Dimension(getWidth(),50));


        tabbedPane.add("Department1",department1Panel);
        tabbedPane.add("Department2",department2Panel);
        tabbedPane.add("Department3",department3Panel);
        tabbedPane.add("Department4",department4Panel);

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == TableModelEvent.UPDATE){

                    int row = e.getFirstRow();
                    Integer ID = (Integer)table.getValueAt(row,1);
                    String price = (String)table.getValueAt(row,2);
                    double p = Double.parseDouble(price);
                    Integer depID = ProductMap.getInstance().get(ID).getDepartmentID();
                    Store.getInstance().modifyProduct(depID, ID, p);

                }
            }

        });

        backButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(root,"Menu");
            }
        });
        sortAlpha.setText("Sort by Name");
        sortAlpha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sortTable("Name");

            }
        });
        sortNum.setText("Sort by Price");
        sortNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sortTable("Price");
            }
        });

        addTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showConfirmDialog(null,inputPanel,"Add product",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

                try {
                    String name = nameField.getText();
                    Integer ID = Integer.parseInt(idField.getText());
                    Integer deptID = Integer.parseInt(deptField.getText());
                    double price = Double.parseDouble(priceField.getText());
                    Store.getInstance().addProduct(deptID, new Item(name, ID, price, deptID));
                    Object []o = new Object[3];
                    o[0] = name;
                    o[1] = ID;
                    o[2] = price;
                    model.addRow(o);
                    table.updateUI();
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Please fill out all fields correctly","Info",JOptionPane.WARNING_MESSAGE);
                }

                nameField.setText("");
                idField.setText("");
                deptField.setText("");
                priceField.setText("");
            }
        });

        delTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int []row = table.getSelectedRows();
                for (int i = row.length - 1; i >= 0; i--) {
                    Integer key = (Integer) model.getValueAt(row[i], 1);
                    Store.getInstance().delProduct(key);
                    ProductMap.getInstance().remove(key);
                    model.removeRow(row[i]);
                }
                    table.updateUI();

            }
        });

        departmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                ArrayList<Department> departments = Store.getInstance().getDepartments();
                generateDepartmentData(departments);

                for(int i = 0; i < 4; i++) {

                    tabbedPane.setTitleAt(i, departments.get(i).getDepartmentName());

                }

                shoppingItems = new ArrayList<>();
                for (int i = 0; i < cust.size(); i++) {
                    shoppingItems.addAll(cust.get(i).getShoppingCart().getItems());

                }

                wishItems = new ArrayList<>();
                for (int i = 0; i < cust.size(); i++) {
                    wishItems.addAll(cust.get(i).getWishList().getItems());

                }

                generateStats();
                cardLayout.show(root,"Department");
            }
        });



/**ADMIN SCREEN*/


/**CLIENT SCREEN*/
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) customerList.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        renderer.setVerticalAlignment(SwingConstants.CENTER);
        shoppingButton = setButton("shopping.png");
        wishButton = setButton("wish.png");
        notificationButton = setButton("notification.png");
        shoppingButton.setText("ShoppingCart");
        notificationButton.setText("Notifications");
        wishButton.setText("WishList");
        okPanel.setLayout(new FlowLayout());
        okPanel.add(shoppingButton);
        okPanel.add(Box.createHorizontalStrut(20));
        okPanel.add(wishButton);
        okPanel.add(Box.createHorizontalStrut(20));
        okPanel.add(notificationButton);
        listPanel.setLayout(new BorderLayout());
        clientTitlePanel.setLayout(new FlowLayout());
        clientTitlePanel.add(clientLabel);
        clientTitlePanel.setPreferredSize(new Dimension(getWidth(),getHeight()/8));
        scrollLIst.setMinimumSize(new Dimension(getWidth()/2,getHeight()/4));
        customerList.setAlignmentX(Component.CENTER_ALIGNMENT);
        customerList.setAlignmentY(Component.CENTER_ALIGNMENT);
        listPanel.add(scrollLIst, BorderLayout.CENTER);
        customerList.setModel(listModel);
        customerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        customerList.setFixedCellHeight(20);
        clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
        clientLabel.setFont(new Font("Serif", Font.ITALIC, 30));
        clientPanel.add(clientTitlePanel);
        clientPanel.add(Box.createVerticalStrut(50));
        clientPanel.add(listPanel);
        clientPanel.add(Box.createVerticalStrut(80));
        clientPanel.add(scrollLIst);
        clientPanel.add(Box.createVerticalStrut(100));
        clientPanel.add(okPanel);
        clientPanel.add(Box.createVerticalStrut(100));
        clientPanel.add(backPanel2);

        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(root,"Menu");
            }
        });

        customerList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!customerList.isSelectionEmpty())
                    isSelected = true;

            }
        });
        wishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSelected) {
                    clientName = customerList.getSelectedValue().getName();
                    wishNameLabel.setText(clientName);
                    wishNameLabel.setFont(new Font("Serif", Font.ITALIC, 30));
                    generateWishList();
                    cardLayout.show(root, "Wish");
                }

            }
        });

        shoppingButton.addMouseListener(new MouseHandler());
        wishButton.addMouseListener(new MouseHandler());
        notificationButton.addMouseListener(new MouseHandler());

        shoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isSelected) {
                    clientName = customerList.getSelectedValue().getName();
                    generateShoppingCartData();
                    cardLayout.show(root, "Shopping");
                }
            }
        });

        notificationPanel.setLayout(new BorderLayout());
        notificationPanel.setMinimumSize(new Dimension(200,200));
        nAuxPanel.setLayout(new FlowLayout());
        nAuxPanel.add(notificationLabel);
        notificationPanel.add(notificationList, BorderLayout.CENTER);
        notificationPanel.add(nAuxPanel, BorderLayout.SOUTH);



        notificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSelected) {

                    for (int i = modelList.size() - 1; i >= 0; i--)
                        modelList.remove(i);

                    String name = customerList.getSelectedValue().getName();
                    ArrayList<Notification> notifications = customers.get(name).getNotifications();
                    System.out.println(notifications);
                    for (int i = 0; i < notifications.size(); i++) {
                        modelList.add(i, notifications.get(i));
                    }

                    if (notifications.size() == 1)
                        notificationLabel.setText(name + " has " + notifications.size() + " notification");
                    else
                        notificationLabel.setText(name + " has " + notifications.size() + " notifications");

                    JOptionPane.showConfirmDialog(null, notificationPanel, "Notifications", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });

/**CLIENT SCREEN*/

/**SHOPPING SCREEN*/

        sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.Y_AXIS));
        namePanel.setLayout(new FlowLayout());
        namePanel.add(nLabel);
        addItemPanel.setLayout(new FlowLayout());

        infoPanel.setLayout(new FlowLayout());

        addItemPanel.add(itemNameLabel);
        addItemPanel.add(comboBox);

        buttonsPanel.setLayout(new FlowLayout());
        addButton = setButton("addTable.png");
        deleteButton = setButton("deleteTable.png");
        purchaseButton = setButton("purchase.png");
        suggestButton = setButton("idea.png");
        suggestButton.setText("Suggest Item");
        addButton.setText("Add");
        deleteButton.setText("Delete");
        purchaseButton.setText("Purchase");
        infoPanel.add(totalLabel);
        infoPanel.add(totalField);
        infoPanel.add(budgetLabel);
        infoPanel.add(budgetField);

        buttonsPanel.add(purchaseButton);
        buttonsPanel.add(Box.createHorizontalStrut(20));
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createHorizontalStrut(20));
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(Box.createHorizontalStrut(20));
        buttonsPanel.add(suggestButton);

        sScroll.setMaximumSize(new Dimension(getWidth(),getHeight()/3));
        sPanel.add(namePanel);
        sPanel.add(Box.createVerticalStrut(40));
        sPanel.add(infoPanel);
        sPanel.add(Box.createVerticalStrut(20));
        sPanel.add(sScroll);
        sPanel.add(Box.createVerticalStrut(40));
        sPanel.add(buttonsPanel);
        sPanel.add(Box.createVerticalStrut(40));
        sPanel.add(backPanel);
        sTable.setModel(sModel);
        sTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        sTable.setDefaultEditor(Object.class,null);  // disables editing in JTable

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showConfirmDialog(null,addItemPanel,"Add product",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
                 Item item = (Item) comboBox.getSelectedItem();

                if(item.getPrice() < customers.get(clientName).getShoppingCart().getBudget()) {
                    customers.get(clientName).addItem(item.getID(), "ShoppingCart");
                    Object[] o = new Object[4];
                    o[0] = item.getID();
                    o[1] = item.getName();
                    o[2] = item.getPrice();
                    o[3] = item.getDepartmentID();
                    Store.getInstance().getDepartment(item.getDepartmentID()).enter(customers.get(clientName));
                    sModel.addRow(o);
                }else {
                    JOptionPane.showMessageDialog(null,"Insufficient funds","Not enough money",JOptionPane.WARNING_MESSAGE);
                }

                budgetField.setText(String.format("%.2f", customers.get(clientName).getShoppingCart().getBudget()));
                totalField.setText(String.format("%.2f", customers.get(clientName).getShoppingCart().getTotalPrice()));
                sTable.updateUI();

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRows[] = sTable.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    Integer integer = (Integer) sModel.getValueAt(selectedRows[i], 0);
                    System.out.println(integer + " " + clientName);
                    customers.get(clientName).delItem(integer, "ShoppingCart");
                    sModel.removeRow(selectedRows[i]);
                }

                totalField.setText(String.format("%.2f", customers.get(clientName).getShoppingCart().getTotalPrice()));
                budgetField.setText(String.format("%.2f", customers.get(clientName).getShoppingCart().getBudget()));


                sTable.updateUI();
            }
        });

        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                File yourFile = new File("caching.wav");
                AudioInputStream stream;
                AudioFormat format;
                DataLine.Info info;
                Clip clip;

                try {
                    stream = AudioSystem.getAudioInputStream(yourFile);
                    format = stream.getFormat();
                    info = new DataLine.Info(Clip.class, format);
                    clip = (Clip) AudioSystem.getLine(info);
                    clip.open(stream);
                    clip.start();
                }
                catch (Exception er ){
                    er.printStackTrace();
                }

                int size = sModel.getRowCount();
                System.out.println(size);
                for (int i = size - 1; i >= 0; i--){
                    sModel.removeRow(i);
                    customers.get(clientName).getNewShoppingCart();
                }

                totalField.setText(String.format("%.2f", customers.get(clientName).getShoppingCart().getTotalPrice()));
                sTable.updateUI();

            }
        });

        suggestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Customer client = customers.get(clientName);

                if (!client.getWishList().isEmpty()) {


                    Item item = client.getWishList().executeStrategy();

                    if (item.getPrice() < client.getShoppingCart().getBudget()) {

                        JOptionPane.showMessageDialog(null, item.getName() + " " + item.getPrice() + " suggested for you", "Suggestions", JOptionPane.INFORMATION_MESSAGE);

                        for (int i = 0; i < client.getWishList().size(); i++)
                            if (( wTable.getValueAt(i, 0)).equals(item.getID())) {
                                wModel.removeRow(i);
                                break;
                            }

                        Object[] obj = new Object[4];
                        obj[0] = item.getID();
                        obj[1] = item.getName();
                        obj[2] = item.getPrice();
                        obj[3] = item.getDepartmentID();
                        sModel.addRow(obj);
                        sTable.updateUI();
                        wTable.updateUI();

                        customers.get(clientName).delItem(item.getID(), "WishList");
                        customers.get(clientName).getShoppingCart().add(item);

                    } else {
                        JOptionPane.showMessageDialog(null, "I would suggest " + item.getName() + " but you cannot afford it for now", "Suggestions", JOptionPane.OK_OPTION);
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null, "Please add something to your WishList to get suggestions", "Suggestions",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        goTowishButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                clientName = customerList.getSelectedValue().getName();
                wishNameLabel.setText(clientName);
                wishNameLabel.setFont(new Font("Serif", Font.ITALIC, 30));
                generateWishList();
                cardLayout.show(root,"Wish");
            }
        });


/**SHOPPING SCREEN*/


/**WISHLIST*/

    wishNamePanel.setLayout(new FlowLayout());
    wishNamePanel.add(wishNameLabel);

    addWishPanel.add(comboBox2);

    addWishButton = setButton("addWish.png");
    addWishButton.setText("Add Wish");
    deleteWishButton = setButton("deleteWish.png");
    deleteWishButton.setText("Delete Wish");

    wishButtonPanel.setLayout(new FlowLayout());
    wishButtonPanel.add(addWishButton);
    wishButtonPanel.add(Box.createHorizontalStrut(20));
    wishButtonPanel.add(deleteWishButton);
    wTable.setModel(wModel);
    wTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

    wTable.setDefaultEditor(Object.class,null);
    wScroll.setMaximumSize(new Dimension(getWidth(),getHeight()/3));
    wishButtonPanel.setPreferredSize(new Dimension(getWidth(),100));
    wPanel.setLayout(new BoxLayout(wPanel, BoxLayout.Y_AXIS));

    wPanel.add(wishNamePanel);
    wPanel.add(Box.createVerticalStrut(40));
    wPanel.add(wScroll);
    wPanel.add(Box.createVerticalStrut(20));
    wPanel.add(wishButtonPanel);
    wPanel.add(Box.createVerticalStrut(40));
    wPanel.add(backPanel3);

    goToShoppingButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            clientName = customerList.getSelectedValue().getName();
            generateShoppingCartData();
            cardLayout.show(root, "Shopping");
         }
    });
    addWishButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            JOptionPane.showConfirmDialog(null,addWishPanel,"Add product",JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);
            Item item = (Item) comboBox2.getSelectedItem();
            customers.get(clientName).addItem(item.getID(), "WishList");
            Object[] o = new Object[4];
            o[0] = item.getID();
            o[1] = item.getName();
            o[2] = item.getPrice();
            o[3] = item.getDepartmentID();
            wModel.addRow(o);
            wTable.updateUI();
        }
    });
    backButton3.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteAllWishTable();
            deleteAllShoppingTable();
            cardLayout.show(root,"Client");
        }
    });
    deleteWishButton.addActionListener(new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            int size = wTable.getSelectedRows().length;
            int []rows = wTable.getSelectedRows();

            for (int i = size - 1; i >= 0; i--){
                customers.get(clientName).delItem((Integer)wTable.getValueAt(rows[i],0), "WishList");
                wModel.removeRow(rows[i]);

            }
            sTable.updateUI();
        }
    });


/**WISHLIST*/

/**TABBEDPANE*/

        addDepartment(observers1Scroll,clients1Scroll,department1Panel,peoplePanel,new JPanel(),new JPanel(),new JPanel(),new JLabel(),
                new JLabel(), new JLabel(),new JLabel(),new JLabel(),new JPanel(), new JButton(), 0);

        addDepartment(observers2Scroll,clients2Scroll,department2Panel,peoplePanel2,new JPanel(),new JPanel(),new JPanel(),new JLabel(),
                new JLabel(),new JLabel(), new JLabel(),new JLabel(),new JPanel(), new JButton(), 1);

        addDepartment(observers3Scroll,clients3Scroll,department3Panel,peoplePanel3,new JPanel(),new JPanel(),new JPanel(),new JLabel(),
                new JLabel(),new JLabel(), new JLabel(),new JLabel(),new JPanel(), new JButton(), 2);

        addDepartment(observers4Scroll,clients4Scroll,department4Panel,peoplePanel4,new JPanel(),new JPanel(),new JPanel(),new JLabel(),
                new JLabel(),new JLabel(), new JLabel(),new JLabel(), new JPanel(), new JButton(), 3);



/**TABBEDPANE*/

        root.add(menu, "Menu");
        root.add(adminPanel,"Admin");
        root.add(clientPanel,"Client");
        root.add(sPanel,"Shopping");
        root.add(wPanel,"Wish");
        root.add(tabbedPane,"Department");
        root.add(loginPanel,"LogIn");
        cardLayout.show(root, "Menu");

        add(root);



    }

    public void generateDepartmentData(ArrayList<Department> departments){

        Customer []observer = (departments.get(0).getObservers()).toArray(new Customer[departments.get(0).getObservers().size()]);
        observers1List.setListData(observer);
        Customer []client = (departments.get(0).getClients()).toArray(new Customer[departments.get(0).getClients().size()]);
        clients1List.setListData(client);
        center(observers1List,clients1List);



        observer = (departments.get(1).getObservers()).toArray(new Customer[departments.get(1).getObservers().size()]);
        observers2List.setListData(observer);
        client = (departments.get(1).getClients()).toArray(new Customer[departments.get(1).getClients().size()]);
        clients2List.setListData(client);
        center(observers2List,clients2List);



        observer = (departments.get(2).getObservers()).toArray(new Customer[departments.get(2).getObservers().size()]);
        observers3List.setListData(observer);
        client = (departments.get(2).getClients()).toArray(new Customer[departments.get(2).getClients().size()]);
        clients3List.setListData(client);
        center(observers3List,clients3List);



        observer = (departments.get(3).getObservers()).toArray(new Customer[departments.get(3).getObservers().size()]);
        observers4List.setListData(observer);
        client = (departments.get(3).getClients()).toArray(new Customer[departments.get(3).getClients().size()]);
        clients4List.setListData(client);
        center(observers4List,clients4List);



    }


    public void center(JList<Customer> observersList, JList<Customer> clientsList){

        observersList.updateUI();
        clientsList.updateUI();

        DefaultListCellRenderer rend = (DefaultListCellRenderer) observersList.getCellRenderer();
        rend.setHorizontalAlignment(SwingConstants.CENTER);
        rend.setVerticalAlignment(SwingConstants.CENTER);

        rend = (DefaultListCellRenderer) clientsList.getCellRenderer();
        rend.setHorizontalAlignment(SwingConstants.CENTER);
        rend.setVerticalAlignment(SwingConstants.CENTER);


    }

    public void addDepartment(JScrollPane observers1Scroll, JScrollPane clients1Scroll, JPanel department1Panel,JPanel peoplePanel,
                              JPanel namePanel, JPanel namePanel2, JPanel labelPanel, JLabel label1, JLabel label2,JLabel label3,
                              JLabel observerNameLabel, JLabel clientNameLabel, JPanel backP, JButton backB, int index){



        Font font = new Font("Serif", Font.BOLD, 15);
        String mostWanted = "";


        backB = setButton("close.png");
        backP.add(backB,BorderLayout.CENTER);
        observerNameLabel.setText("Observers");
        clientNameLabel.setText("Clients");

        observerNameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        clientNameLabel.setFont(new Font("Serif", Font.BOLD, 20));

        observerNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        clientNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.add(observerNameLabel);
        namePanel.add(Box.createVerticalStrut(20));
        namePanel.add(observers1Scroll);

        namePanel2.setLayout(new BoxLayout(namePanel2, BoxLayout.Y_AXIS));
        namePanel2.add(clientNameLabel);
        namePanel2.add(Box.createVerticalStrut(20));
        namePanel2.add(clients1Scroll);

        peoplePanel.setLayout(new FlowLayout());

        observers1Scroll.setPreferredSize(new Dimension(getWidth()/2,getHeight()/2));
        clients1Scroll.setPreferredSize(new Dimension(getWidth()/2 ,getHeight()/2));
        peoplePanel.add(namePanel);
        peoplePanel.add(namePanel2);

        labelPanel.setPreferredSize(new Dimension(getWidth(), Math.round(getHeight()/4) - 20));
        backP.setPreferredSize(new Dimension(getWidth(), getHeight()/8 - 20));



        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        label1.setText("The most wanted product ");
        label2.setText("The most expensive product");
        label3.setText("The best-selling product ");

        labels[indexLabel] = label1;
        labels[indexLabel + 1] = label2;
        labels[indexLabel + 2] = label3;

        indexLabel += 3;

        label1.setFont(font);
        label2.setFont(font);
        label3.setFont(font);

        labelPanel.add(Box.createVerticalStrut(40));
        labelPanel.add(label1);
        labelPanel.add(Box.createVerticalStrut(10));
        labelPanel.add(label2);
        labelPanel.add(Box.createVerticalStrut(10));
        labelPanel.add(label3);

        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);

        backB.setText("Close Tabs");

        department1Panel.add(peoplePanel);
        department1Panel.add(labelPanel);
        department1Panel.add(backP);

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 cardLayout.show(root, "Admin");
            }
        });

    }


    public void updateTableData(ArrayList<Item> array){

        int i = 0;


        for (i = 0; i < array.size(); i++){

            model.removeRow(i);
            Object []o = new Object[3];
            o[0] = array.get(i).getName();
            o[1] = array.get(i).getID();
            o[2] = array.get(i).getPrice();
            model.insertRow(i,o);
         }

    }



    public void deleteAllShoppingTable(){

        int size = sTable.getRowCount();

        for (int i = size - 1; i >= 0; i--){
            sModel.removeRow(i);
        }

        sTable.updateUI();

    }

    public void deleteAllWishTable(){

        int size = wTable.getRowCount();

        for (int i = size - 1; i >= 0; i--){
            wModel.removeRow(i);
        }

        wTable.updateUI();
    }

    public void generateShoppingCartData(){

            ArrayList<Item> items = customers.get(clientName).getShoppingCart().getItems();
            nLabel.setText(clientName);
            nLabel.setFont(new Font("Serif", Font.ITALIC, 30));
            ArrayList<Item> allItems = new ArrayList<>(ProductMap.getInstance().values());

          totalField.setText(String.format("%.2f", customers.get(clientName).getShoppingCart().getTotalPrice()));
          budgetField.setText(String.format("%.2f", customers.get(clientName).getShoppingCart().getBudget()));

            for (Item item : allItems)
                comboBox.addItem(item);

            if(sModel.getRowCount() == 0) {

                for (Item it : items) {
                    Object[] obj = new Object[4];
                    obj[0] = it.getID();
                    obj[1] = it.getName();
                    obj[2] = it.getPrice();
                    obj[3] = it.getDepartmentID();
                    sModel.addRow(obj);
                }

            }

    }

    public void generateWishList(){

        ArrayList<Item> allItems = new ArrayList<>(ProductMap.getInstance().values());
        for (Item item : allItems)
            comboBox2.addItem(item);

        ArrayList<Item> items = customers.get(clientName).getWishList().getItems();

        if(wModel.getRowCount() == 0) {

            for (Item it : items) {
                Object[] obj = new Object[4];
                obj[0] = it.getID();
                obj[1] = it.getName();
                obj[2] = it.getPrice();
                obj[3] = it.getDepartmentID();
                wModel.addRow(obj);
            }

        }

    }

    public void createTableData(){

        int i = 0;

        for (Map.Entry<Integer, Item> entry : ProductMap.getInstance().entrySet()){

            Item item = entry.getValue();
            Object []o  = new Object[3];
            o[0] = item.getName();
            o[1] = item.getID();
            o[2] = item.getPrice();
            model.insertRow(i,o);
            i++;
        }

     }


    public void sortTable(String identifier){

        ArrayList<Item> array = new ArrayList<Item>(ProductMap.getInstance().values());

        if(!alreadySortedAlpha) {

            if(identifier.equals("Name"))
                Collections.sort(array, new ItemList.NameComparator());
            else if(identifier.equals("Price"))
                Collections.sort(array, new ItemList.ItemComparator());

            alreadySortedAlpha = true;
        }
        else {
            if(identifier.equals("Name"))
                Collections.sort(array, new ItemList.descNameComparator());
            else if(identifier.equals("Price"))
                Collections.sort(array, new ItemList.descItemComparator());

            alreadySortedAlpha = false;
        }

        updateTableData(array);
        table.updateUI();


    }



    public void paintRootScreen(){

        menu.setBackground(BLUE);
        menu.add(titlePanel);
        menu.add(browsePanel);
        menu.add(radios);
        menu.add(ePanel);
        titlePanel.setLayout(new FlowLayout());
        title.setFont(new Font("Serif", Font.ITALIC, 30));
        titlePanel.add(title);
        titlePanel.setMaximumSize(new Dimension(getWidth(),getHeight()/4));
        titlePanel.setBackground(BLUE);
        browsePanel.setLayout(new BoxLayout(browsePanel, BoxLayout.Y_AXIS));
        browsePanel.setMaximumSize(new Dimension(getWidth(),getHeight()/2));
        browsePanel.add(chooseFiles);
        browsePanel.add(Box.createVerticalStrut(20));
        browsePanel.add(browseButton);
        browsePanel.add(Box.createVerticalStrut(10));
        browsePanel.add(browseLabel);
        browsePanel.add(Box.createVerticalStrut(30));

        h.setSize(new Dimension(40,40));
        h.add(new JLabel("Password: "));
        h.setBackground(BLUE);
        browsePanel.add(Box.createHorizontalStrut(20));
        h.add(pass);

        h.setVisible(false);

        browsePanel.add(h);
        browsePanel.setBackground(BLUE);

        adminButton.setText("Admin");
        clientButton.setText("Client");
        radios.add(adminButton);
        radios.add(clientButton);
        clientButton.setSelected(true);
        adminButton.setBackground(BLUE);
        clientButton.setBackground(BLUE);
        group.add(adminButton);
        group.add(clientButton);
        radios.setLayout(new FlowLayout());
        radios.setMaximumSize(new Dimension(getWidth(),getHeight()/8));
        radios.setBackground(BLUE);
        ePanel.setBackground(BLUE);
        ePanel.setLayout(new FlowLayout());
        ePanel.add(exitButton);
        ePanel.add(okButton);
        ePanel.setMaximumSize(new Dimension(getWidth(),getHeight()/8));

        chooseFiles.setAlignmentX(Component.CENTER_ALIGNMENT);
        browseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        browseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menu.setLayout(new BoxLayout(menu,BoxLayout.Y_AXIS));

        sPanel.setLayout(new BorderLayout());
        sPanel.add(backPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAllWishTable();
                deleteAllShoppingTable();
                cardLayout.previous(root);
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                h.setVisible(true);
            }
        });

        browseButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame frame = new JFrame("Choose your file");
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setApproveButtonText("Choose");
                        fileChooser.setPreferredSize(new Dimension(800,500));
                        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        frame.add(fileChooser);
                        fileChooser.setMultiSelectionEnabled(true);
                        int result = fileChooser.showSaveDialog(fileChooser);
                        if (result == JFileChooser.APPROVE_OPTION) {
                            File[] files = fileChooser.getSelectedFiles();

                            if (files.length == 2) {
                                parseFiles(files);
                                okButton.setEnabled(true);
                                frame.setVisible(false);
                                frame.dispose();
                                enabledOK = true;
                            }
                            else {
                                JOptionPane.showMessageDialog(null,"You have to choose 2 files: store.txt and customers.txt","Info",JOptionPane.WARNING_MESSAGE);
                            }
                        }


                    }
                });
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(enabledOK) {
                    if(adminButton.isSelected()) {
                        System.out.println(ProductMap.getInstance());
                        char []password = pass.getPassword();

                        if (checkPassword(password))
                         cardLayout.show(root, "Admin");
                        else
                            JOptionPane.showMessageDialog(null,"Wrong Password","Info",JOptionPane.WARNING_MESSAGE);


                    }
                    else if(clientButton.isSelected())
                       cardLayout.show(root,"Client");
                }

            }
        });
    }

    public boolean checkPassword(char[] myPass){
        try {
            boolean ok = true;
            FileInputStream fileIn = new FileInputStream("p");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            String password = (String) in.readObject();

            if (password.length() == myPass.length) {
                for (int i = 0; i < password.length(); i++) {
                    if (password.charAt(i) != myPass[i]) {
                        ok = false;
                        break;
                    }
                }
            }
            else
                ok = false;

            in.close();
            fileIn.close();

            return ok;

        }catch(IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException exc){
            exc.printStackTrace();
        }

        return false;
    }

    public void parseFiles(File []files){

        String customersPath = "";
        String storePath = "";
        ok = 0;
        for (int i = 0; i < files.length; i++) {
            String path = files[i].getAbsolutePath();
            String s = "";

            StringTokenizer st = new StringTokenizer(path, "\\/");
            while (st.hasMoreElements()) {
                s = st.nextToken();
            }

            if (s.equals("customers.txt")) {
                ok++;
                customersPath = path;
                System.out.println(customersPath);
            }
            if (s.equals("store.txt")) {
                ok++;
                storePath = path;
                System.out.println(storePath);

            }
        }


        if (ok == 2) {

            store = null;
            customers = new HashMap<>();
            store = Test.readStore(storePath);
            customers = Test.readCustormers(customersPath);
            createTableData();
            createCustomersData();
            cust = new ArrayList<>(customers.values());

        }
        else {
            JOptionPane.showMessageDialog(null,"The files should be called store.txt and customers.txt","Info",JOptionPane.WARNING_MESSAGE);
        }

    }

    public void createCustomersData(){

        ArrayList<Customer> customerNames = new ArrayList<>(customers.values());
        for (int i = 0; i < customerNames.size(); i++)
            listModel.add(i,customerNames.get(i));

    }

    public JButton setButton(String name){

        try {
            BufferedImage image = ImageIO.read(new File(name));
            JButton button = new JButton(new ImageIcon(image));
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            return button;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public void generateStats(){


        getWantedProducts(shoppingItems, popular);
        getWantedProducts(wishItems, wanted);


        for (int i = 0; i < 4; i++) {

            double max = 0;
            Integer maxID = 0;

            Department d = Store.getInstance().getDepartments().get(i);
            ArrayList<Item> arrays = d.getItems();

            for (int j = 0; j < arrays.size(); j++)
                if(arrays.get(j).getPrice() > max){
                    max = arrays.get(j).getPrice();
                    maxID = arrays.get(j).getID();
                }

            if (!popular.isEmpty()) {
                if (Store.getInstance().getDepartments().get(i).getID().equals(ProductMap.getInstance().get(popular.get(i)).getDepartmentID()))  // daca sunt din departamentul respectiv
                    labels[3 * i + 2].setText("The best selling product is: " + ProductMap.getInstance().get(popular.get(i)).getName());
                else
                    labels[3 * i + 2].setText("The best selling product is: No product in this department ");
            }
            else
                labels[3 * i + 2].setText("The best selling product is: No product in this department ");

            if (!wanted.isEmpty()) {
                if (Store.getInstance().getDepartments().get(i).getID().equals(ProductMap.getInstance().get(wanted.get(i)).getDepartmentID()))  // daca sunt din departamentul respectiv
                    labels[3 * i].setText("The most wanted product is: " + ProductMap.getInstance().get(wanted.get(i)).getName());
                else
                    labels[3 * i].setText("The most wanted product is: No product in this department ");
            }
            else
                labels[3 * i].setText("The most wanted product is: No product in this department ");

            Item it = ProductMap.getInstance().get(maxID);
            if (it != null)
                labels[3 * i + 1].setText("The most expensive product is: " +  it.getName() + " (" + it.getPrice() + ")");
            else
                labels[3 * i + 1].setText("The most expensive product is: No product in this department");

        }


    }

    public void removeObservers(){

        ArrayList<Department> d = Store.getInstance().getDepartments();

        for (int i = 0; i < d.size(); i++)
            d.get(i).removeAllObservers();


    }

    public void getWantedProducts(ArrayList<Item> array, ArrayList<Integer> stats){

        for (int i = 0; i < 4; i++) {

            HashMap<Integer, Integer> appearance = new HashMap<>();
            Integer deptID = Store.getInstance().getDepartments().get(i).getID();

            for (int j = 0; j < array.size(); j++)
                appearance.put(array.get(j).getID(),0);

            for (Item item : array) {
                if (item.getDepartmentID().equals(deptID)) {
                    Integer count = appearance.get(item.getID());
                    if (count != null)
                        appearance.put(item.getID(), count + 1);
                }
            }

            if (!array.isEmpty()) {
                Integer mostPopularID = Collections.max(appearance.entrySet(), new Comparator<Map.Entry<Integer, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                }).getKey();

                stats.add(i,mostPopularID);
            }

        }

    }

    class MouseHandler implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(!isSelected) {
                shoppingButton.setToolTipText("Please select a customer first");
                wishButton.setToolTipText("Please select a customer first");
                notificationButton.setToolTipText("Please select a customer first");
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if(!isSelected) {
                shoppingButton.setToolTipText("Please select a customer first");
                wishButton.setToolTipText("Please select a customer first");
                notificationButton.setToolTipText("Please select a customer first");
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}

