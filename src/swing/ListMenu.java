package swing;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import model.Model1_Menu;


public class ListMenu<E extends Object> extends JList<E> {
    
    private final DefaultListModel model;
    private int selectedIndex=-1;
    public ListMenu(){
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter(){
        @Override
        public void mousePressed(MouseEvent me){
        if(SwingUtilities.isLeftMouseButton(me)){
        int index = locationToIndex(me.getPoint());
        Object o =model.getElementAt(index);
        if (o instanceof Model1_Menu){
        Model1_Menu menu=(Model1_Menu)o;
        if(menu.getType()==Model1_Menu.MenuType.MENU){
        selectedIndex=index;
        }
        }else{
            selectedIndex=index;
        }
        repaint();
        }
        }
        });
        
    }
    
    @Override
    public ListCellRenderer<? super E> getCellRenderer(){
        return new DefaultListCellRenderer(){
        @Override
        public Component getListCellRendererComponent(JList<?> jList, Object o, int index, boolean selected, boolean focus){
         Model1_Menu data;
        if(o instanceof Model1_Menu){
         data=(Model1_Menu)o;
        }else{
         data= new Model1_Menu("",o+"",Model1_Menu.MenuType.EMPTY);
        }
        MenuItem item=new MenuItem(data);
        item.setSelected(selectedIndex==index);
        return item;
        }
        };
    }
    public void addItem(Model1_Menu data){
    model.addElement(data);
    }
}
