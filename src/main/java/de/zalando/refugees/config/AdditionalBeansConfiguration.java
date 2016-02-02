package de.zalando.refugees.config;

import de.zalando.refugees.domain.Demand;
import de.zalando.refugees.web.rest.dto.DemandDTO;
import de.zalando.refugees.web.rest.mapper.DemandMapper;
import de.zalando.refugees.web.rest.mapper.implementation.AppUserMapperImpl;
import de.zalando.refugees.web.rest.mapper.implementation.BranchMapperImpl;
import de.zalando.refugees.web.rest.mapper.implementation.OrganizationMapperImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AdditionalBeansConfiguration  
{
	@Bean
	public OrganizationMapperImpl organizationMapperImpl()
	{
		return new OrganizationMapperImpl();
	}
	
	@Bean
	public BranchMapperImpl branchMapperImpl()
	{
		return new BranchMapperImpl();
	}
	
	@Bean
	public AppUserMapperImpl appUserMapperImpl()
	{
		return new AppUserMapperImpl();
	}
}
