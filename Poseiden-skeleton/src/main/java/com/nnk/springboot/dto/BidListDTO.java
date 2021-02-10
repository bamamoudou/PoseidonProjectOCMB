package com.nnk.springboot.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class groups information related to bid list.
 *
 * @author Yahia CHERIFI
 */


public class BidListDTO {

    /**
     * BidList id.
     */
    private Integer bidListId;

    /**
     * account.
     */
    @NotBlank(message = "Account is mandatory")
    private String account;

    /**
     * type.
     */
    @NotNull
    @NotBlank(message = "Type is mandatory")
    private String type;

    /**
     * bid quantity.
     */
    @NotNull(message = "Bid quantity is mandatory and must be a number")
    private Double bidQuantity;

    /**
     * ask quantity.
     */
    private Double askQuantity;

    /**
     * bid.
     */
    private Double bid;

    /**
     * ask.
     */
    private Double ask;

    /**
     * benchmark.
     */
    private String benchmark;

    /**
     * bid list date.
     */
    private Timestamp bidListDate;

    /**
     * commentary.
     */
    private String commentary;

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
     *  bidListDate getter.
     * @return bidList date
     */
    public Timestamp getBidListDate() {
        if (bidListDate == null) {
            return null;
        } else {
            return new Timestamp(bidListDate.getTime());
        }
    }

    /**
     * bidListDate setter.
     * @param bListDate date
     */
    public void setBidListDate(final Timestamp bListDate) {
        this.bidListDate = new Timestamp(bListDate.getTime());
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
     * @param bCreationDate creation date
     */
    public void setCreationDate(final Timestamp bCreationDate) {
        this.creationDate = new Timestamp(bCreationDate.getTime());
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
     * @param bRevisionDate date
     */
    public void setRevisionDate(final Timestamp bRevisionDate) {
        if (bRevisionDate == null) {
            this.revisionDate = null;
        } else {
            this.revisionDate = new Timestamp(bRevisionDate.getTime());
        }
    }

	public Integer getBidListId() {
		return bidListId;
	}

	public void setBidListId(Integer bidListId) {
		this.bidListId = bidListId;
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

	public Double getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(Double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public Double getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(Double askQuantity) {
		this.askQuantity = askQuantity;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
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