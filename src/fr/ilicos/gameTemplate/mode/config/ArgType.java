package fr.ilicos.gameTemplate.mode.config;

import org.apache.commons.lang.StringUtils;

/**
 * Created by ilicos, Théo S. on 14/08/2015.
 */
public enum ArgType {
    MIXTE {
        @Override
        public boolean validArg(String[] args) {
            return true;
        }
    },
    STRING {
        @Override
        public boolean validArg(String[] args) {
            return true;
        }
    },
    INT {
        @Override
        public boolean validArg(String[] args) {
            for (String arg: args){
                if (!StringUtils.isNumeric(arg)){
                    return false;
                }
            }
            return true;
        }
    },
    POSITIVE_INT {
        @Override
        public boolean validArg(String[] args) {
            for (String arg: args){
                try{
                    int num = Integer.parseInt(arg);

                    if (num < 0){
                        return false;
                    }
                } catch (NumberFormatException ignored) {
                    return false;
                }
            }

            return true;
        }
    },
    NEGATIVE_INT {
        @Override
        public boolean validArg(String[] args) {
            for (String arg: args){
                try{
                    int num = Integer.parseInt(arg);

                    if (num > 0){
                        return false;
                    }
                } catch (NumberFormatException ignored) {
                    return false;
                }
            }

            return true;
        }
    },
    NULL {
        @Override
        public boolean validArg(String[] args) {
            return args.length == 0;
        }
    };

    public abstract boolean validArg(String[] args);
}
