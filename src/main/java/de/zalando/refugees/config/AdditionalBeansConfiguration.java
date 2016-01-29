package de.zalando.refugees.config;

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
	public BranchMapperImpl BranchMapperImpl()
	{
		return new BranchMapperImpl();
	}

}
