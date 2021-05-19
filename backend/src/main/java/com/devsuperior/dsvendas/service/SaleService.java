package com.devsuperior.dsvendas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsvendas.dto.SaleDTO;
import com.devsuperior.dsvendas.entities.Sale;
import com.devsuperior.dsvendas.repositories.SaleRepository;
import com.devsuperior.dsvendas.repositories.SellerRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	@Autowired
	private SellerRepository sellerRepository;
	
	/**
	 * Busca paginada com os resultados
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)//operação somente leitura
	public Page<SaleDTO> findAll(Pageable pageable){
		/**
		 * Usado para fazer cacheamento dos resultado em memória para não ficar 
		 * fazendo consultas constantes no banco
		 * Usar somente quando tem poucos resultados por página
		 */
		sellerRepository.findAll();
		
		Page<Sale> result = repository.findAll(pageable);
		return result.map(x -> new SaleDTO(x));
	}
}
