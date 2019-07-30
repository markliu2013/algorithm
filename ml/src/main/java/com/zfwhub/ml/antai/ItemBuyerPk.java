package com.zfwhub.ml.antai;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ItemBuyerPk implements Serializable {

    private static final long serialVersionUID = 9011422205212866950L;

    @Column(name="buyer_admin_id")
    private Integer buyerAdminId;
    
    @Column(name="irank")
    private Integer iRank;

    public Integer getBuyerAdminId() {
        return buyerAdminId;
    }

    public void setBuyerAdminId(Integer buyerAdminId) {
        this.buyerAdminId = buyerAdminId;
    }

    public Integer getiRank() {
        return iRank;
    }

    public void setiRank(Integer iRank) {
        this.iRank = iRank;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((buyerAdminId == null) ? 0 : buyerAdminId.hashCode());
        result = prime * result + ((iRank == null) ? 0 : iRank.hashCode());
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
        ItemBuyerPk other = (ItemBuyerPk) obj;
        if (buyerAdminId == null) {
            if (other.buyerAdminId != null)
                return false;
        } else if (!buyerAdminId.equals(other.buyerAdminId))
            return false;
        if (iRank == null) {
            if (other.iRank != null)
                return false;
        } else if (!iRank.equals(other.iRank))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ItemBuyerPk [buyerAdminId=" + buyerAdminId + ", iRank=" + iRank + "]";
    }

}
