package ca.project.presentation.android.dependencyinjection;

import android.util.Log;

import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Stream;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.CommandHandlers;
import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

import ca.project.domain.external.infrastructure.couchbase.IUserRepository;
import ca.project.presentation.android.UserRepository;
import ca.project.service.ICommandValidation;
import ca.project.service.ValidationMiddleware;
import ca.project.service.user.CreateUser.CreateUserRequestValidator;
import ca.project.service.user.CreateUser.CreateUserRequest;

@Module
public class AppModule {

    @Provides
    ModelMapper providesModelMapper() {
        return new ModelMapper();
    }

    @Provides
    IUserRepository providesUserRepository() {
        return new UserRepository();
    }

    @IntoSet
    @Provides
    Command.Handler providesCreateUserRequestHandler(ModelMapper modelMapper, IUserRepository userRepository) {
        return new CreateUserRequest.Handler(modelMapper, userRepository);
    }

    @IntoSet
    @Provides
    ICommandValidation providesCreateUserRequestValidator() {
        return new CreateUserRequestValidator();
    }

    @Provides
    CommandHandlers providesCommandHandlers(Set<Command.Handler> handlers){
        return new PipelinrHandlers(handlers);
    }

    Command.Middleware providesCommandMiddleware(Set<ICommandValidation> validators){
        return new ValidationMiddleware(validators);
    }

    @Provides
    Pipeline pipeline(CommandHandlers commandHandlers, Set<ICommandValidation> validators){
        Log.v("AppMod", "test " + validators.size());
        return new Pipelinr()
                .with(commandHandlers)
                .with(() -> Stream.of(new ValidationMiddleware(validators)));
    }
}
