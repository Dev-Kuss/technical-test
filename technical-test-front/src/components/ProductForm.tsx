import { useState, useEffect } from 'react';
import { 
    Dialog, DialogTitle, DialogContent, DialogActions,
    TextField, Button, FormControl, InputLabel,
    Select, MenuItem, FormControlLabel, Switch,
    SelectChangeEvent
} from '@mui/material';
import { Product, PhysicalProduct, DigitalProduct } from '../types/Product';

interface ProductFormProps {
    open: boolean;
    onClose: () => void;
    onSave: (product: Omit<Product, 'id'>) => void;
    product?: Product;
}

export const ProductForm = ({ open, onClose, onSave, product }: ProductFormProps) => {
    const [type, setType] = useState<'PhysicalProduct' | 'DigitalProduct'>('PhysicalProduct');
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');
    const [onSale, setOnSale] = useState(false);
    const [weight, setWeight] = useState('');
    const [sizeMB, setSizeMB] = useState('');

    useEffect(() => {
        if (product) {
            setType(product['@type']);
            setName(product.name);
            setPrice(product.price.toString());
            setOnSale(product.onSale);
            if ('weight' in product) {
                setWeight(product.weight.toString());
            }
            if ('sizeMB' in product) {
                setSizeMB(product.sizeMB.toString());
            }
        }
    }, [product]);

    const handleSubmit = () => {
        const baseProduct = {
            name,
            price: parseFloat(price),
            onSale
        };

        let newProduct: Omit<Product, 'id'>;
        if (type === 'PhysicalProduct') {
            newProduct = {
                ...baseProduct,
                '@type': 'PhysicalProduct',
                weight: parseFloat(weight)
            } as PhysicalProduct;
        } else {
            newProduct = {
                ...baseProduct,
                '@type': 'DigitalProduct',
                sizeMB: parseFloat(sizeMB)
            } as DigitalProduct;
        }

        onSave(newProduct);
        handleClose();
    };

    const handleClose = () => {
        setType('PhysicalProduct');
        setName('');
        setPrice('');
        setOnSale(false);
        setWeight('');
        setSizeMB('');
        onClose();
    };

    const handleTypeChange = (event: SelectChangeEvent) => {
        setType(event.target.value as 'PhysicalProduct' | 'DigitalProduct');
    };

    return (
        <Dialog open={open} onClose={handleClose} maxWidth="sm" fullWidth>
            <DialogTitle>{product ? 'Edit Product' : 'New Product'}</DialogTitle>
            <DialogContent>
                <FormControl fullWidth margin="normal">
                    <InputLabel>Type</InputLabel>
                    <Select value={type} onChange={handleTypeChange}>
                        <MenuItem value="PhysicalProduct">Physical Product</MenuItem>
                        <MenuItem value="DigitalProduct">Digital Product</MenuItem>
                    </Select>
                </FormControl>

                <TextField
                    fullWidth
                    margin="normal"
                    label="Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />

                <TextField
                    fullWidth
                    margin="normal"
                    label="Price"
                    type="number"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                />

                <FormControlLabel
                    control={
                        <Switch
                            checked={onSale}
                            onChange={(e) => setOnSale(e.target.checked)}
                        />
                    }
                    label="On Sale"
                />

                {type === 'PhysicalProduct' ? (
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Weight (kg)"
                        type="number"
                        value={weight}
                        onChange={(e) => setWeight(e.target.value)}
                    />
                ) : (
                    <TextField
                        fullWidth
                        margin="normal"
                        label="Size (MB)"
                        type="number"
                        value={sizeMB}
                        onChange={(e) => setSizeMB(e.target.value)}
                    />
                )}
            </DialogContent>
            <DialogActions>
                <Button onClick={handleClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">
                    Save
                </Button>
            </DialogActions>
        </Dialog>
    );
};
