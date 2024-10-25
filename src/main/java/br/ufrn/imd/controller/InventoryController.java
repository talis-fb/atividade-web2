package br.ufrn.imd.controller;

import br.ufrn.imd.model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final List<Item> inventory = new ArrayList<>();

    @GetMapping
    public String listInventory(Model model) {
        model.addAttribute("inventory", inventory);
        return "inventory/list";
    }

    @GetMapping("/new")
    public String newItem(Model model) {
        model.addAttribute("item", new Item());
        return "inventory/form";
    }

    @PostMapping("/save")
    public String saveItem(@ModelAttribute Item item) {
        inventory.add(item);
        return "redirect:/inventory";
    }

    @GetMapping("/edit/{index}")
    public String editItem(@PathVariable int index, Model model) {
        model.addAttribute("item", inventory.get(index));
        model.addAttribute("index", index);
        return "inventory/form";
    }

    @PostMapping("/update/{index}")
    public String updateItem(@PathVariable int index, @ModelAttribute Item item) {
        inventory.set(index, item);
        return "redirect:/inventory";
    }

    @GetMapping("/delete/{index}")
    public String deleteItem(@PathVariable int index) {
        inventory.remove(index);
        return "redirect:/inventory";
    }
}
