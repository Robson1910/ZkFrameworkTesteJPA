package io.github.robson.controller;

import io.github.robson.connection.ConnectionDAO;
import io.github.robson.model.Contato;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

import java.util.ArrayList;

public class Controller extends SelectorComposer<Component> implements ControllerInterface{

    @Wire
    private Textbox NomeCon;
    @Wire
    private Textbox phoneCon;
    @Wire
    private Textbox emailCon;
    @Wire
    private Textbox NomeEdit;
    @Wire
    private Textbox phoneEdit;
    @Wire
    private Textbox emailEdit;
    @Wire
    private Label IdEdit;
    @Wire
    private Listbox listBox;

    ConnectionDAO dao = new ConnectionDAO();

    @Listen("onClick = #buttonSave")
    @Override
    public void Save() {
        if(NomeCon.getValue().isEmpty() || NomeCon == null ||
                phoneCon.getValue().isEmpty() || phoneCon == null ||
                emailCon.getValue().isEmpty() || emailCon == null
        ){
            Messagebox.show("Is Empty.");
        }else{
            dao.Insert(new Contato(null,NomeCon.getValue(), phoneCon.getValue(), emailCon.getValue()));
            FindAll();
        }
    }

    @Listen("onCreate = #listBox")
    @Override
    public void FindAll()  {
        ArrayList<Contato> contatoLista = new ArrayList<>(dao.GetAll());
        listBox.setModel(new ListModelArray<>(contatoLista));
    }

    @Listen("onSelect = #listBox")
    @Override
    public void Select() {
        Contato contato;
        contato = listBox.getSelectedItem().getValue();
        IdEdit.setValue(contato.getId());
        NomeEdit.setValue(contato.getNome());
        phoneEdit.setValue(contato.getFone());
        emailEdit.setValue(contato.getEmail());
        System.out.println(dao.Get(contato.getId()));
    }

    @Listen("onClick = #buttonEdit")
    @Override
    public void Edit() {
        if(IdEdit.getValue().isEmpty() || IdEdit == null ){
            Messagebox.show("Is Empty.");
        }else{
            dao.Update(new Contato(IdEdit.getValue(),NomeEdit.getValue(), phoneEdit.getValue(), emailEdit.getValue()));
            FindAll();
            Clear();
        }
    }

    @Listen("onClick = #buttonRemove")
    @Override
    public void Remove() {
        if(IdEdit.getValue().isEmpty() || IdEdit == null ){
            Messagebox.show("Is Empty.");
        }else{
            dao.Delete(IdEdit.getValue());
            FindAll();
            Clear();
        }
    }

    @Listen("onClick = #buttonClear")
    @Override
    public void Clear() {
        IdEdit.setValue("");
        NomeEdit.setValue("");
        phoneEdit.setValue("");
        emailEdit.setValue("");
    }
}
