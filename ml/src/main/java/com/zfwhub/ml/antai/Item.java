package com.zfwhub.ml.antai;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 商品实体类
@Entity
@Table(name="item_arr")
public class Item {
    @Id
    @Column(name="item_id")
    private Integer itemId;
    
    @Column(name="cate_id")
    private Integer cateId;
    
    @Column(name="store_id")
    private Integer storeId;
    
    @Column(name="item_price")
    private Integer itemPrice;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
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
        Item other = (Item) obj;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Item [itemId=" + itemId + ", cateId=" + cateId + ", storeId=" + storeId + ", itemPrice=" + itemPrice + "]";
    }

}
