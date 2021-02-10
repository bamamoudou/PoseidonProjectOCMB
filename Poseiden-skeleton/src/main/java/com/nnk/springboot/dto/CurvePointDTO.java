package com.nnk.springboot.dto;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

/**
 * This class groups information related to curve point.
 *
 * @author Yahia CHERIFI
 */

public class CurvePointDTO {

    /**
     * CurvePoint id.
     */
    private Integer curvePointId;

    /**
     * curve id.
     */
    @NotNull(message = "Curve id is mandatory")
    private Integer curveId;

    /**
     * as of date.
     */
    private Timestamp asOfDate;

    /**
     * term.
     */
    @NotNull(message = "Term is mandatory")
    private Double term;

    /**
     * value.
     */
    @NotNull(message = "Value id is mandatory")
    private Double value;

    /**
     * creation date.
     */
    private Timestamp creationDate;

    /**
     * asOfDate getter.
     * @return getAsOf date
     */
    public Timestamp getAsOfDate() {
        if (asOfDate == null) {
            return null;
        } else {
            return new Timestamp(asOfDate.getTime());
        }
    }

    /**
     * asOfDate setter.
     * @param cAsOfDate date
     */
    public void setAsOfDate(final Timestamp cAsOfDate) {
        if (cAsOfDate == null) {
            this.creationDate = null;
        } else {
            this.asOfDate = new Timestamp(cAsOfDate.getTime());
        }
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
     * @param cCreationDate date
     */
    public void setCreationDate(final Timestamp cCreationDate) {
        this.creationDate = new Timestamp(cCreationDate.getTime());
    }

	public Integer getCurvePointId() {
		return curvePointId;
	}

	public void setCurvePointId(Integer curvePointId) {
		this.curvePointId = curvePointId;
	}

	public Integer getCurveId() {
		return curveId;
	}

	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
    
}
