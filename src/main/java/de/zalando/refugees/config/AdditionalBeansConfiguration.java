package de.zalando.refugees.config;

import de.zalando.refugees.web.rest.mapper.BranchMapper;
import de.zalando.refugees.web.rest.mapper.OrganizationMapper;
import de.zalando.refugees.web.rest.mapper.implementation.BranchMapperImpl;
import de.zalando.refugees.web.rest.mapper.implementation.OrganizationMapperImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AdditionalBeansConfiguration
{
	@Bean
	public OrganizationMapper organizationMapper()
	{
		return new OrganizationMapperImpl();
	}

	@Bean
	public BranchMapper branchMapper()
	{
		return new BranchMapperImpl();
	}

}
