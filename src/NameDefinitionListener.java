import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import java.util.*;
public class NameDefinitionListener extends MinijavaBaseListener {
    Map<String, Klass> klasses;
    Klass currentKlass;
    Klass.Method currentMethod = null;
    public NameDefinitionListener(Map<String, Klass> klasses){
        this.klasses=klasses;
    }
    @Override public void enterClassDeclaration(@NotNull MinijavaParser.ClassDeclarationContext ctx) { 
        currentKlass = new Klass(ctx.Identifier(0).getText(), true);
        klasses.put(currentKlass.name, currentKlass);
    }
    @Override public void exitClassDeclaration(@NotNull MinijavaParser.ClassDeclarationContext ctx) {
        currentKlass=null;
    }
    @Override public void enterMainClass(@NotNull MinijavaParser.MainClassContext ctx) {
        currentKlass = new Klass("int[]", true);
        klasses.put(currentKlass.name,currentKlass);
        currentKlass = new Klass("int", false);
        klasses.put(currentKlass.name,currentKlass);
        currentKlass = new Klass("boolean", false);
        klasses.put(currentKlass.name,currentKlass);
        currentKlass=null;
    }
    @Override public void enterVarDeclaration(@NotNull MinijavaParser.VarDeclarationContext ctx) {
        //if(currentMethod==null){
        //    currentKlass.fields.put(ctx.Identifier().getText(), null);
        //}
    }
    @Override public void enterMethodDeclaration(@NotNull MinijavaParser.MethodDeclarationContext ctx) {
        //System.out.println("The method signature for class " + currentKlass + " is " + Main.getMethodSignature(ctx));
        //currentMethod = new Klass.Method(Main.getMethodSignature(ctx));
    }
    @Override public void exitMethodDeclaration(@NotNull MinijavaParser.MethodDeclarationContext ctx) {
        currentKlass.methods.put(currentMethod.name, currentMethod);
        currentMethod = null;
    }
    //@Override public void enterParameterList(@NotNull MinijavaParser.ParameterListContext ctx) {currentMethod.name+="(";}
    //@Override public void exitParameterList(@NotNull MinijavaParser.ParameterListContext ctx) {currentMethod.name+=")";}
    @Override public void enterParameter(@NotNull MinijavaParser.ParameterContext ctx) {
    }
}