package org.atzitz.core.plugin.actions.args;

import lombok.Getter;
import org.atzitz.core.plugin.actions.args.validation.ValidateChain;

@Getter
public record Arg(String name, ValidateChain validateChain) {

}
