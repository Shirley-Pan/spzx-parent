package com.atguigu.spzx.manager.model.entity.product;

import com.atguigu.spzx.manager.model.entity.base.BaseEntity;
import lombok.Data;

@Data
public class ProductDetails extends BaseEntity {

	private Long productId;
	private String imageUrls;

}