package de.zalando.refugees.config;

import de.zalando.refugees.domain.util.DemandComparator;
import de.zalando.refugees.service.GeoCodingService;
import de.zalando.refugees.web.rest.mapper.implementation.AppUserMapperImpl;
import de.zalando.refugees.web.rest.mapper.implementation.BranchMapperImpl;
import de.zalando.refugees.web.rest.mapper.implementation.DemandMapperImpl;
import de.zalando.refugees.web.rest.mapper.implementation.OfferMapperImpl;
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

	@Bean
	public GeoCodingService geoCodingService()
	{
		return new GeoCodingService();
	}

	@Bean
	public DemandComparator demandComparator()
	{
		return new DemandComparator();
	}

	@Bean
	public DemandMapperImpl demandMapperImpl()
	{
		return new DemandMapperImpl();
	}

	@Bean
	public OfferMapperImpl offerMapperImpl()
	{
		return new OfferMapperImpl();
	}
}
