import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BSFrame extends JFrame {

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet result = null;

    int id = -1;
    int idCategory = -1;
    int idLocation = -1;


    JTabbedPane tabbedPane = new JTabbedPane();

    JPanel bsPanel = new JPanel();

    JPanel bsPanel1 = new JPanel();
    JPanel bsPanel2 = new JPanel();
    JPanel bsPanel3 = new JPanel();


    //ПАНЕЛ ЗА УСЛУГИ
    JLabel serviceNameL = new JLabel("Услуга:");
    JLabel serviceLocationL = new JLabel("Локация:");
    JLabel serviceCategoryL = new JLabel("Категория:");
    JLabel serviceDescrL = new JLabel("Описание:");
    JLabel servicePriceL = new JLabel("Цена:");
    JLabel serviceTimeL = new JLabel("Времетраене (в минути):");

    private static final long serialVersionUID = 4L;

    JTextField serviceNameTF = new JTextField();
    JTextField serviceDescrTF = new JTextField();
    JTextField servicePriceTF = new JTextField();
    JTextField serviceTimeTF = new JTextField();

    JComboBox<String> locationCombo = new JComboBox<String>();
    JComboBox<String> categoryCombo = new JComboBox<String>();

    JButton addButton = new JButton("Добави");
    JButton deleteButton = new JButton("Изтрий");
    JButton editButton = new JButton("Редактирай");

    JTable servTable = new JTable();
    JScrollPane servScroll = new JScrollPane(servTable);


    //ПАНЕЛ ЗА ЛОКАЦИЯ
    JLabel locationNameL = new JLabel("Локация: ");
    JTextField locationNameTF = new JTextField();
    JPanel locationPanel = new JPanel();
    JPanel locationPanel1 = new JPanel();
    JPanel locationPanel2 = new JPanel();
    JPanel locationPanel3 = new JPanel();
    JButton locAddButton = new JButton("Добави");
    JButton locDeleteButton = new JButton("Изтрий");
    JButton locEditButton = new JButton("Редактирай");
    JTable locationTable = new JTable();
    JScrollPane locationScroll = new JScrollPane(locationTable);

    //ПАНЕЛ ЗА КАТЕГОРИЯ
    JLabel categoryNameL = new JLabel("Категория: ");
    JTextField categoryNameTF = new JTextField();
    JPanel categoryPanel = new JPanel();
    JPanel categoryPanel1 = new JPanel();
    JPanel categoryPanel2 = new JPanel();
    JPanel categoryPanel3 = new JPanel();
    JButton catAddButton = new JButton("Добави");
    JButton catDeleteButton = new JButton("Изтрий");
    JButton catEditButton = new JButton("Редактирай");
    JTable categoryTable = new JTable();
    JScrollPane categoryScroll = new JScrollPane(categoryTable);

    //ПАНЕЛ ЗА ТЪРСЕНЕ
    JLabel searchL = new JLabel("Въведете категория за търсене:");
    JTextField searchTF = new JTextField();
    JPanel searchPanel = new JPanel();
    JPanel searchPanel1 = new JPanel();
    JPanel searchPanel2 = new JPanel();
    JPanel searchPanel3 = new JPanel();
    JButton searchSearchButton = new JButton("Търси");
    JTable searchTable = new JTable();
    JScrollPane searchScroll = new JScrollPane(searchTable);

    public BSFrame(){

        connection = MyDBConnection.getConnection();

        this.setSize(600, 720);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Козметични услуги");

        //TAB УСЛУГИ
        this.tabbedPane.add(this.bsPanel, "Услуги");
        this.bsPanel.setLayout(new GridLayout(3, 1));

        this.bsPanel.add(this.bsPanel1);
        this.bsPanel1.setLayout(new GridLayout(7, 2));
        this.bsPanel1.add(this.serviceNameL);
        this.bsPanel1.add(this.serviceNameTF);
        this.bsPanel1.add(this.serviceCategoryL);
        this.bsPanel1.add(this.categoryCombo);
        this.bsPanel1.add(this.serviceLocationL);
        this.bsPanel1.add(this.locationCombo);
        this.bsPanel1.add(this.serviceDescrL);
        this.bsPanel1.add(this.serviceDescrTF);
        this.bsPanel1.add(this.servicePriceL);
        this.bsPanel1.add(this.servicePriceTF);
        this.bsPanel1.add(this.serviceTimeL);
        this.bsPanel1.add(this.serviceTimeTF);

        this.bsPanel.add(this.bsPanel2);
        this.bsPanel2.add(this.addButton);
        this.bsPanel2.add(this.deleteButton);
        this.bsPanel2.add(this.editButton);
        this.addButton.addActionListener(new AddServiceDB());
        this.deleteButton.addActionListener(new DelServiceDB());
        this.editButton.addActionListener(new EditServiceDB());

        this.bsPanel.add(this.bsPanel3);
        this.servScroll.setPreferredSize(new Dimension(550, 200));
        this.bsPanel3.add(this.servScroll);

        //TAB ЛОКАЦИЯ
        this.tabbedPane.add(this.locationPanel, "Локация");
        this.locationPanel.setLayout(new GridLayout(3, 1));

        this.locationPanel.add(this.locationPanel1);
        this.locationPanel1.add(this.locationNameL);
        this.locationPanel1.add(this.locationNameTF);
        this.locationNameTF.setPreferredSize(new Dimension(450, 30));

        this.locationPanel.add(locationPanel2);
        this.locationPanel2.add(locAddButton);
        this.locationPanel2.add(locDeleteButton);
        this.locationPanel2.add(locEditButton);

        this.locationPanel.add(this.locationPanel3);
        this.locationScroll.setPreferredSize(new Dimension(550, 200));
        this.locationPanel3.add(this.locationScroll);
        this.locAddButton.addActionListener(new AddLocationDB());
        this.locDeleteButton.addActionListener(new DelLocationDB());
        this.locEditButton.addActionListener(new EditLocationDB());

        //TAB КАТЕГОРИЯ
        this.tabbedPane.add(this.categoryPanel, "Категория");
        this.categoryPanel.setLayout(new GridLayout(3, 1));

        this.categoryPanel.add(this.categoryPanel1);
        this.categoryPanel1.add(this.categoryNameL);
        this.categoryPanel1.add(this.categoryNameTF);
        this.categoryNameTF.setPreferredSize(new Dimension(450, 30));

        this.categoryPanel.add(categoryPanel2);
        this.categoryPanel2.add(catAddButton);
        this.categoryPanel2.add(catDeleteButton);
        this.categoryPanel2.add(catEditButton);
        this.catAddButton.addActionListener(new AddCategoryDB());
        this.catDeleteButton.addActionListener(new DelCategoryDB());
        this.catEditButton.addActionListener(new EditCategoryDB());

        this.categoryPanel.add(this.categoryPanel3);
        this.categoryScroll.setPreferredSize(new Dimension(550, 200));
        this.categoryPanel3.add(this.categoryScroll);

        //TAB ТЪРСЕНЕ
        this.tabbedPane.add(this.searchPanel, "Търсене");
        this.searchPanel.setLayout(new GridLayout(3, 1));

        this.searchPanel.add(this.searchPanel1);
        this.searchPanel1.add(this.searchL);
        this.searchPanel1.add(this.searchTF);
        this.searchTF.setPreferredSize(new Dimension(450, 30));

        this.searchPanel.add(this.searchPanel2);
        this.searchPanel2.add(this.searchSearchButton);
        this.searchSearchButton.addActionListener(new SearchDB());

        this.searchPanel.add(this.searchPanel3);
        this.searchScroll.setPreferredSize(new Dimension(550, 200));
        this.searchPanel3.add(this.searchScroll);


        //OTHER
        this.add(this.tabbedPane);
        this.setVisible(true);
        this.refreshServiceTable();
        this.refreshComboCategory();
        this.refreshComboLocation();
        this.refreshCategoryTable("CATEGORYTABLE", this.categoryTable);
        this.refreshLocationTable("LOCATIONTABLE", this.locationTable);
        this.servTable.addMouseListener(new MouseActionServiceTable());
        this.categoryTable.addMouseListener(new MouseActionCategoryTable());
        this.locationTable.addMouseListener(new MouseActionLocationTable());

        this.categoryCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (BSFrame.this.tabbedPane.getSelectedIndex() == 0 && BSFrame.this.idCategory > 0) {
                    String str = "select * from CATEGORYTABLE where CATEGORYNAME ='" + BSFrame.this.categoryCombo.getSelectedItem().toString() + "'";

                    try {
                        BSFrame.this.statement = BSFrame.this.connection.prepareStatement(str);
                        BSFrame.this.result = BSFrame.this.statement.executeQuery();
                        BSFrame.this.result.next();
                        BSFrame.this.idCategory = Integer.parseInt(BSFrame.this.result.getObject(1).toString());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println(BSFrame.this.categoryCombo.getSelectedItem().toString());
                    System.out.println(str);
                    System.out.println(BSFrame.this.idCategory);
                }

            }
        });

        this.locationCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (BSFrame.this.tabbedPane.getSelectedIndex() == 0 && BSFrame.this.idLocation > 0) {
                    String str = "select * from LOCATIONTABLE where LOCATIONNAME='" + BSFrame.this.locationCombo.getSelectedItem().toString() + "'";

                    try {
                        BSFrame.this.statement = BSFrame.this.connection.prepareStatement(str);
                        BSFrame.this.result = BSFrame.this.statement.executeQuery();
                        BSFrame.this.result.next();
                        BSFrame.this.idLocation = Integer.parseInt(BSFrame.this.result.getObject(1).toString());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    System.out.println(BSFrame.this.locationCombo.getSelectedItem().toString());
                    System.out.println(str);
                    System.out.println(BSFrame.this.idLocation);
                }
            }
        });
    }

    //SERVICE ACTIONS

    public void refreshServiceTable() {
        String str = "";
        str = "select SERVICETABLE.id, name, CATEGORYTABLE.CATEGORYNAME as CATEGORY, SERVICETABLE.CATEGORYID, " +
                "LOCATIONTABLE.LOCATIONNAME AS LOCATION, SERVICETABLE.LOCATIONID, DESCRIPTION, PRICE, TIME  " +
                "from SERVICETABLE, CATEGORYTABLE, LOCATIONTABLE " +
                "where SERVICETABLE.CATEGORYID = CATEGORYTABLE.ID and SERVICETABLE.LOCATIONID = LOCATIONTABLE.ID";

        try {
            this.statement = this.connection.prepareStatement(str);
            this.result = this.statement.executeQuery();
            this.servTable.setModel(new MyTModel(this.result));
            this.servTable.getColumnModel().getColumn(3).setMinWidth(0);
            this.servTable.getColumnModel().getColumn(3).setMaxWidth(0);
            this.servTable.getColumnModel().getColumn(3).setWidth(0);
            this.servTable.getColumnModel().getColumn(5).setMinWidth(0);
            this.servTable.getColumnModel().getColumn(5).setMaxWidth(0);
            this.servTable.getColumnModel().getColumn(5).setWidth(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }

    public void refreshComboLocation() {
        this.idLocation = -1;
        this.locationCombo.removeAllItems();
        String sql = "select ID, LOCATIONNAME from LOCATIONTABLE";
        String item = "";

        try {
            this.statement = this.connection.prepareStatement(sql);
            this.result = this.statement.executeQuery();
            if (this.result.next()) {
                this.idLocation = Integer.parseInt(this.result.getObject(1).toString());

                do {
                    item = this.result.getObject(2).toString();
                    this.locationCombo.addItem(item);
                } while(this.result.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void refreshComboCategory() {
        this.idCategory = -1;
        this.categoryCombo.removeAllItems();
        String sql = "select ID, CATEGORYNAME from CATEGORYTABLE";
        String item = "";

        try {
            this.statement = this.connection.prepareStatement(sql);
            this.result = this.statement.executeQuery();
            if (this.result.next()) {
                this.idCategory = Integer.parseInt(this.result.getObject(1).toString());

                do {
                    item = this.result.getObject(2).toString();
                    this.categoryCombo.addItem(item);
                } while(this.result.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    class AddServiceDB implements ActionListener {
        AddServiceDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!BSFrame.this.serviceNameTF.getText().isEmpty()) {
                String sql = "insert into SERVICETABLE(NAME, CATEGORYID, LOCATIONID, DESCRIPTION, PRICE, TIME) values(?,?,?,?,?,?)";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setString(1, BSFrame.this.serviceNameTF.getText());
                    BSFrame.this.statement.setInt(2, BSFrame.this.idCategory);
                    BSFrame.this.statement.setInt(3, BSFrame.this.idLocation);
                    BSFrame.this.statement.setString(4, BSFrame.this.serviceDescrTF.getText());
                    BSFrame.this.statement.setDouble(5, Double.parseDouble(BSFrame.this.servicePriceTF.getText()));
                    BSFrame.this.statement.setInt(6, Integer.parseInt(BSFrame.this.serviceTimeTF.getText()));
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshServiceTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.serviceNameTF.setText("");
                BSFrame.this.serviceDescrTF.setText("");
                BSFrame.this.servicePriceTF.setText("");
                BSFrame.this.serviceTimeTF.setText("");
                BSFrame.this.id = -1;
            }
        }
    }

    class DelServiceDB implements ActionListener {
        DelServiceDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (BSFrame.this.id > 0) {
                String sql = "delete from SERVICETABLE where id=?";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setInt(1, BSFrame.this.id);
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshServiceTable();
                } catch (SQLException var4) {
                    var4.printStackTrace();
                }

                BSFrame.this.serviceNameTF.setText("");
                BSFrame.this.serviceDescrTF.setText("");
                BSFrame.this.servicePriceTF.setText("");
                BSFrame.this.serviceTimeTF.setText("");
                BSFrame.this.id = -1;
            }
        }
    }

    class EditServiceDB implements ActionListener {
        EditServiceDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (BSFrame.this.id > 0) {
                String sql = "update SERVICETABLE set NAME=?, CATEGORYID=?, LOCATIONID=?, DESCRIPTION=?, PRICE=?, TIME=? where ID=?";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setString(1, BSFrame.this.serviceNameTF.getText());
                    BSFrame.this.statement.setInt(2, BSFrame.this.idCategory);
                    BSFrame.this.statement.setInt(3, BSFrame.this.idLocation);
                    BSFrame.this.statement.setString(4, BSFrame.this.serviceDescrTF.getText());
                    BSFrame.this.statement.setDouble(5, Double.parseDouble(BSFrame.this.servicePriceTF.getText()));
                    BSFrame.this.statement.setInt(6, Integer.parseInt(BSFrame.this.serviceTimeTF.getText()));
                    BSFrame.this.statement.setInt(7, BSFrame.this.id);
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshServiceTable();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.serviceNameTF.setText("");
                BSFrame.this.serviceDescrTF.setText("");
                BSFrame.this.servicePriceTF.setText("");
                BSFrame.this.serviceTimeTF.setText("");
            }
        }
    }

    class MouseActionServiceTable implements MouseListener {

        MouseActionServiceTable() {
        }

        public void mouseClicked(MouseEvent e) {

            int row = BSFrame.this.servTable.getSelectedRow();
            BSFrame.this.id = Integer.parseInt(BSFrame.this.servTable.getValueAt(row, 0).toString());
            BSFrame.this.serviceNameTF.setText(BSFrame.this.servTable.getValueAt(row, 1).toString());
            BSFrame.this.categoryCombo.setSelectedItem(BSFrame.this.servTable.getValueAt(row, 2).toString());
            BSFrame.this.idCategory = Integer.parseInt(BSFrame.this.servTable.getValueAt(row, 3).toString());
            BSFrame.this.locationCombo.setSelectedItem(BSFrame.this.servTable.getValueAt(row, 4).toString());
            BSFrame.this.idLocation = Integer.parseInt(BSFrame.this.servTable.getValueAt(row, 5).toString());
            BSFrame.this.serviceDescrTF.setText(BSFrame.this.servTable.getValueAt(row, 6).toString());
            BSFrame.this.servicePriceTF.setText(BSFrame.this.servTable.getValueAt(row, 7).toString());
            BSFrame.this.serviceTimeTF.setText(BSFrame.this.servTable.getValueAt(row, 8).toString());

        }

        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }

    }

    //CATEGORY ACTIONS
    public void refreshCategoryTable(String name, JTable table) {
        String str = "select * from " + name;

        try {
            this.statement = this.connection.prepareStatement(str);
            this.result = this.statement.executeQuery();
            table.setModel(new MyTModel(this.result));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }

    }

    class AddCategoryDB implements ActionListener {
        AddCategoryDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!BSFrame.this.categoryNameTF.getText().isEmpty()) {
                String sql = "insert into CATEGORYTABLE(CATEGORYNAME) values(?)";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setString(1, BSFrame.this.categoryNameTF.getText());
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshCategoryTable("CATEGORYTABLE", BSFrame.this.categoryTable);
                    BSFrame.this.refreshComboCategory();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.categoryNameTF.setText("");
            }
        }
    }

    class DelCategoryDB implements ActionListener {
        DelCategoryDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (BSFrame.this.idCategory > 0) {
                String sql = "delete from CATEGORYTABLE where ID=?";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setInt(1, BSFrame.this.idCategory);
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshCategoryTable("CATEGORYTABLE", BSFrame.this.categoryTable);
                    BSFrame.this.refreshComboCategory();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.categoryNameTF.setText("");
            }
        }
    }

    class EditCategoryDB implements ActionListener {
        EditCategoryDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (BSFrame.this.idCategory > 0) {
                String sql = "update CATEGORYTABLE set CATEGORYNAME=? where ID=?";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setString(1, BSFrame.this.categoryNameTF.getText());
                    BSFrame.this.statement.setInt(2, BSFrame.this.idCategory);
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshCategoryTable("CATEGORYTABLE", BSFrame.this.categoryTable);
                    BSFrame.this.refreshComboCategory();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.categoryNameTF.setText("");
            }
        }
    }

    class MouseActionCategoryTable implements MouseListener {
        MouseActionCategoryTable() {
        }

        public void mouseClicked(MouseEvent e) {
            int row = BSFrame.this.categoryTable.getSelectedRow();
            BSFrame.this.idCategory = Integer.parseInt(BSFrame.this.categoryTable.getValueAt(row, 0).toString());
            BSFrame.this.categoryNameTF.setText(BSFrame.this.categoryTable.getValueAt(row, 1).toString());
        }

        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
        }
    }



    //LOCATION ACTIONS

    public void refreshLocationTable(String name, JTable table) {
        String str = "select * from " + name;

        try {
            this.statement = this.connection.prepareStatement(str);
            this.result = this.statement.executeQuery();
            table.setModel(new MyTModel(this.result));
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex2) {
            ex2.printStackTrace();
        }

    }



    class AddLocationDB implements ActionListener {
        AddLocationDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!BSFrame.this.locationNameTF.getText().isEmpty()) {
                String sql = "insert into LOCATIONTABLE(LOCATIONNAME) values(?)";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setString(1, BSFrame.this.locationNameTF.getText());
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshLocationTable("LOCATIONTABLE", BSFrame.this.locationTable);
                    BSFrame.this.refreshComboLocation();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.locationNameTF.setText("");
            }
        }
    }





    class DelLocationDB implements ActionListener {
        DelLocationDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (BSFrame.this.idLocation > 0) {
                String sql = "delete from LOCATIONTABLE where ID=?";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setInt(1, BSFrame.this.idLocation);
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshLocationTable("LOCATIONTABLE", BSFrame.this.locationTable);
                    BSFrame.this.refreshComboLocation();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.locationNameTF.setText("");
            }
        }
    }




    class EditLocationDB implements ActionListener {
        EditLocationDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (BSFrame.this.idLocation > 0) {
                String sql = "update LOCATIONTABLE set LOCATIONNAME=? where ID=?";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(sql);
                    BSFrame.this.statement.setString(1, BSFrame.this.locationNameTF.getText());
                    BSFrame.this.statement.setInt(2, BSFrame.this.idLocation);
                    BSFrame.this.statement.execute();
                    BSFrame.this.refreshLocationTable("LOCATIONTABLE", BSFrame.this.locationTable);
                    BSFrame.this.refreshComboLocation();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                BSFrame.this.locationNameTF.setText("");
            }
        }
    }



    class MouseActionLocationTable implements MouseListener {
        MouseActionLocationTable() {
        }

        public void mouseClicked(MouseEvent e) {
            int row = BSFrame.this.locationTable.getSelectedRow();
            BSFrame.this.idLocation = Integer.parseInt(BSFrame.this.locationTable.getValueAt(row, 0).toString());
            BSFrame.this.locationNameTF.setText(BSFrame.this.locationTable.getValueAt(row, 1).toString());
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }

    //SEARCH ACTIONS
    class SearchDB implements ActionListener {
        SearchDB() {
        }

        public void actionPerformed(ActionEvent actionEvent) {
            if (!BSFrame.this.searchTF.getText().isEmpty()) {
                String str = "select SERVICETABLE.ID, NAME, CATEGORYTABLE.CATEGORYNAME, SERVICETABLE.CATEGORYID, " +
                        "LOCATIONTABLE.LOCATIONNAME, SERVICETABLE.LOCATIONID, DESCRIPTION, PRICE, TIME " +
                        "from SERVICETABLE, CATEGORYTABLE, LOCATIONTABLE " +
                        "where SERVICETABLE.CATEGORYID = CATEGORYTABLE.ID and SERVICETABLE.LOCATIONID = LOCATIONTABLE.ID " +
                        "and CATEGORYTABLE.CATEGORYNAME ='" + BSFrame.this.searchTF.getText() + "'";

                try {
                    BSFrame.this.statement = BSFrame.this.connection.prepareStatement(str);
                    BSFrame.this.result = BSFrame.this.statement.executeQuery();
                    BSFrame.this.searchTable.setModel(new MyTModel(BSFrame.this.result));
                    BSFrame.this.searchTable.getColumnModel().getColumn(3).setMinWidth(0);
                    BSFrame.this.searchTable.getColumnModel().getColumn(3).setMaxWidth(0);
                    BSFrame.this.searchTable.getColumnModel().getColumn(3).setWidth(0);
                    BSFrame.this.searchTable.getColumnModel().getColumn(5).setMinWidth(0);
                    BSFrame.this.searchTable.getColumnModel().getColumn(5).setMaxWidth(0);
                    BSFrame.this.searchTable.getColumnModel().getColumn(5).setWidth(0);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                }

                BSFrame.this.searchTF.setText("");
            }

        }
    }





}
