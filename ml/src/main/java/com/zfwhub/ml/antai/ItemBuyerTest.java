package com.zfwhub.ml.antai;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "an_test")
public class ItemBuyerTest {

    @Id
    private ItemBuyerPk itemBuyerPk;
    
    @ManyToOne
    @JoinColumn(name="item_id", foreignKey=@ForeignKey(name="none", value=ConstraintMode.NO_CONSTRAINT))
    private Item item;
    
    @Column(name="create_order_time")
    private LocalDate createOrderTime;
    
    @Column(name="buyer_country_id")
    private String buyerCountryId;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(LocalDate createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    public String getBuyerCountryId() {
        return buyerCountryId;
    }

    public void setBuyerCountryId(String buyerCountryId) {
        this.buyerCountryId = buyerCountryId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemBuyerPk == null) ? 0 : itemBuyerPk.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemBuyerTest other = (ItemBuyerTest) obj;
        if (itemBuyerPk == null) {
            if (other.itemBuyerPk != null)
                return false;
        } else if (!itemBuyerPk.equals(other.itemBuyerPk))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ItemBuyerTrain [itemBuyerPk=" + itemBuyerPk + ", item=" + item + ", createOrderTime=" + createOrderTime + ", buyerCountryId=" + buyerCountryId + "]";
    }

}
