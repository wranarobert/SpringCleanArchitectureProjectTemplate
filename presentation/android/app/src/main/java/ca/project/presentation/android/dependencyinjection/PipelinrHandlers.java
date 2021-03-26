package ca.project.presentation.android.dependencyinjection;

import android.util.Log;

import java.util.Set;
import java.util.stream.Stream;

import an.awesome.pipelinr.Command;
import an.awesome.pipelinr.CommandHandlers;

public class PipelinrHandlers implements CommandHandlers{

    Set<Command.Handler> handlers;

    public PipelinrHandlers(Set<Command.Handler> handlers){
        Log.v("AppMod", "pipehandlers = " + handlers.size());
        this.handlers = handlers;
    }

    @Override
    public Stream<Command.Handler> supply() {
        return handlers.stream();
    }
}
