package org.atzitz.core.plugin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.atzitz.core.plugin.phases.IPhase;
import org.atzitz.core.plugin.roles.IRole;
import org.atzitz.core.plugin.teams.ITeam;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Getter
@RequiredArgsConstructor
public class PluginLoader {
    private final Collection<ITeam> teams;
    private final Collection<IPhase> phases;
    private final Collection<IRole> roles;
}
