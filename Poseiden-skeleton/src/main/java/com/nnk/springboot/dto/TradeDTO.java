package com.nnk.springboot.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class TradeDTO {

    /**
     * trade id.
     */
    private Integer tradeId;

    /**
     * account.
     */
    @NotNull
    @NotBlank(message = "Account is mandatory")
    private String account;

    /**
     * type.
     */
    @NotNull
    @NotBlank(message = "Type is mandatory")
    private String type;

    /**
     * buy quantity.
     */
    private Double buyQuantity;

    /**
     * sell quantity.
     */
    private Double sellQuantity;

    /**
     * buy price.
     */
    private Double buyPrice;

    /**
     * sell price.
     */
    private Double sellPrice;

    /**
     * benchmark.
     */
    private String benchmark;

    /**
     * trade date.
     */
    private Timestamp tradeDate;

    /**
     * security.
     */
    private String security;

    /**
     * status.
     */
    private String status;

    /**
     * trader.
     */
    private String trader;

    /**
     * book.
     */
    private String book;

    /**
     * creation name.
     */
    private String creationName;

    /**
     * creation date.
     */
    private Timestamp creationDate;

    /**
     * revision name.
     */
    private String revisionName;

    /**
     * revision date.
     */
    private Timestamp revisionDate;

    /**
     * deal name.
     */
    private String dealName;

    /**
     * deal type.
     */
    private String dealType;

    /**
     * source list id.
     */
    private String sourceListId;

    /**
     * side.
     */
    private String side;

    /**
     * tradeDate getter.
     * @return trade date
     */
    public Timestamp getTradeDate() {
        if (tradeDate == null) {
            return null;
        } else {
            return new Timestamp(tradeDate.getTime());
        }
    }

    /**
     * tradeDate setter.
     * @param tTradeDate date
     */
    public void setTradeDate(final Timestamp tTradeDate) {
        this.tradeDate = new Timestamp(tTradeDate.getTime());
    }

    /**
     * creationDate getter.
     * @return creation date
     */
    public Timestamp getCreationDate() {
        if (creationDate == null) {
            return null;
        } else {
            return new Timestamp(creationDate.getTime());
        }
    }

    /**
     * creationDate setter.
     * @param tCreationDate date
     */
    public void setCreationDate(final Timestamp tCreationDate) {
        this.creationDate = new Timestamp(tCreationDate.getTime());
    }

    /**
     * revisionDate getter.
     * @return revision date
     */
    public Timestamp getRevisionDate() {
        if (revisionDate == null) {
            return null;
        } else {
            return new Timestamp(revisionDate.getTime());
        }
    }

    /**
     * revisionDate setter.
     * @param tRevisionDate date
     */
    public void setRevisionDate(final Timestamp tRevisionDate) {
        if (tRevisionDate == null) {
            this.revisionDate = null;
        } else {
            this.revisionDate = new Timestamp(tRevisionDate.getTime());
        }
    }

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCreationName() {
		return creationName;
	}

	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getSourceListId() {
		return sourceListId;
	}

	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}
    
    
}
