package cinema;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Seat extends JPanel {

    private Cinema cinema;
    private final int column, row;
    private boolean free = true;
    private boolean vip;
    private final int price = 300;
    private final int vipPrice = 600;

    public Seat(int column, int row, int x, int y, int width, int height, Cinema c) {
        setBounds(x, y, width, height); //установка размера и позиции квадратиков
        
        cinema = c; //экземпляр главного класса (нужен для установки свободного кол-ва мест, и общей цены
        setVisible(true);
        add(new JLabel((column + 1) + "")); //создает текствовый label для номера места
        this.column = column;
        this.row = row;

        if (column > 6 && column < 14 && row > 2 && row < 8) { //установка VIP мест в середине зала
            setBackground(Color.pink); 
            vip = true;
        } else { //установка обычных мест
            vip = false;
            setBackground(Color.blue);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { //когда жмем мышкой на место
                free = !free; //место меняет статус свободное/занятое
                changeColor(); //меняет цвет
                changePrice(); //меняет цену
                
                cinema.setJLabel8Text(cinema.fullPrice+""); //установка полной цены
                cinema.setJLabel11Text(cinema.count+"/200"); //установка кол-ва мест
            }
        });
    }
    
    public void changeColor(){
        if (free){ //если место свободное
            if (vip) //если VIP, то цвет места меняем на розовый, иначе синий
                setBackground(Color.pink);
            else
                setBackground(Color.blue);
        }
        else //а если место занято, то цвет - красный
            setBackground(Color.red);
    }
    
    public void changePrice(){
        if (!free){ //если место заняли, прибавляем стоимость
            if (vip)
                cinema.fullPrice += vipPrice;
            else
                cinema.fullPrice += price;
            cinema.count += 1;
        }
        else{ //если место освободили, прибавляем стоимость
            if (vip)
                cinema.fullPrice -= vipPrice;
            else
                cinema.fullPrice -= price;
            cinema.count -= 1;
        }
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isFree() {
        return free;
    }

    public boolean isVip() {
        return vip;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

}
