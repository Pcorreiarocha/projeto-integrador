package com.faculdade;

import com.faculdade.commons.mapper.CustomValueReader;
import org.hibernate.Hibernate;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.record.RecordModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetoIntegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoIntegradorApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		modelMapper.getConfiguration().setPropertyCondition( Conditions.isNotNull());
		modelMapper.getConfiguration().addValueReader(new CustomValueReader());
		modelMapper.getConfiguration().setMatchingStrategy( MatchingStrategies.STRICT);
		modelMapper.getConfiguration().setPropertyCondition(context -> Hibernate.isInitialized(context.getSource()));
		modelMapper.registerModule( new RecordModule() );
		return modelMapper;
	}

}
