package com.nnk.springboot.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class groups information related to Rating.
 *
 * @author Yahia CHERIFI
 */

public class RatingDTO {

    /**
     * Rating id.
     */
    private Integer id;

    /**
     * moody's rating.
     */
    @NotBlank(message = "Moody's Rating is mandatory")
    private String moodysRating;

    /**
     * s and p rating.
     */
    @NotBlank(message = "S & P Rating is mandatory")
    private String sandPRating;

    /**
     * fitch rating.
     */
    @NotBlank(message = "Fitch Rating is mandatory")
    private String fitchRating;

    /**
     * order number.
     */
    @NotNull(message = "Order number is mandatory")
    private Integer orderNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
    
    
}
